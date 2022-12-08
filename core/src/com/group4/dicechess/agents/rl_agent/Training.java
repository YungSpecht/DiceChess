package com.group4.dicechess.agents.rl_agent;

import com.group4.dicechess.GameState;
import com.group4.dicechess.Representation.Board;
import com.group4.dicechess.Representation.Move;
import com.group4.dicechess.Representation.Piece;
import com.group4.dicechess.agents.rl_agent.utils.Action;
import com.group4.dicechess.agents.rl_agent.utils.Experience;
import com.group4.dicechess.agents.rl_agent.utils.Input;
import com.group4.dicechess.agents.rl_agent.utils.QValues;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

import static com.group4.dicechess.agents.rl_agent.network.NetworkParameters.gamma;
import static com.group4.dicechess.agents.rl_agent.network.NetworkParameters.inputChannels;
import static com.group4.dicechess.agents.rl_agent.utils.Utils.*;

/*| 1 | Pawn   |
  | 2 | Knight |
  | 3 | Bishop |
  | 4 | Rook   |
  | 5 | Queen  |
  | 6 | King   |*/


public class Training {

    private final GameState gameState;

    private final RL_Agent whiteAgent, blackAgent;

    private final double LIVING_COST = -0.1;
    private final int MOVE_LIMIT = 100;
    private final int KING_CAPTURE_VALUE = 10;
    private final Random random = new Random();
    public static int totalMoves;
    private final int numGames = 50;

    public Training() throws Exception {
        gameState = new GameState();

        whiteAgent = new RL_Agent(true);
        blackAgent = new RL_Agent(false);

        whiteAgent.loadAgentWeights();
        blackAgent.loadAgentWeights();

        TrainingLoop();
    }


    private void TrainingLoop() throws Exception {

        int episode = 0;
        totalMoves = 0;

        while (episode++ < numGames) {
            gameLoop();
            gameState.reset();
        }

        whiteAgent.saveAgentWeights();
        blackAgent.saveAgentWeights();
    }

    // TODO: Backpropagation:
    //  - End game training
    //  - Test
    //  - Optimiser (Adam)
    // TODO: Link GUI
    private void gameLoop() throws Exception {

        Experience whiteExp, blackExp;

        gameState.getBoard().printBoard();

        whiteExp = decide(true);
        gameState.movePiece(whiteExp.action().move());

        blackExp = decide(false);
        gameState.movePiece(blackExp.action().move());

        while (gameState.turnCounter < MOVE_LIMIT){

            trainAgent(true, whiteExp, blackExp);

            whiteExp = decide(true);
            gameState.movePiece(whiteExp.action().move());
            print("End Turn: White");

            if (gameState.isGameOver()){
                print("Game Over");
                gameState.getBoard().printBoard();
                endTrainAgents(true, whiteExp, blackExp);
                return;
            }

            //trainAgent(false, whiteExp, blackExp);

            blackExp = decide(false);
            gameState.movePiece(blackExp.action().move());
            print("End Turn: Black");

            if (gameState.isGameOver()){
                print("Game Over");
                gameState.getBoard().printBoard();
                //endTrainAgents(false, whiteExp, blackExp);
                return;
            }
        }
    }

    private Experience decide(boolean white) throws Exception {
        printTurn(gameState);

        HashMap<Piece, ArrayList<Move>> pieceMoveMap = new HashMap<>();
        ArrayList<Piece> pieces = gameState.getBoard().getAllPieces();
        Board board = gameState.getBoard();

        for (Piece piece : pieces)
            pieceMoveMap.put(piece, piece.getPossibleMoves(board));

        int diceRoll = rollDice(white, pieceMoveMap, pieces);
        Input input = getInput(white, diceRoll, pieceMoveMap, pieces);

        if (white) return whiteAgent.predictMove(input);
        return blackAgent.predictMove(input);
    }
    
    private int rollDice(boolean white,HashMap<Piece, ArrayList<Move>> pieceMoveMap, ArrayList<Piece> pieces){

        boolean selected = false;
        Piece piece = null;

        while (!selected) {
            piece = pieces.get(random.nextInt(pieces.size()));
            if (piece.getWhiteStatus() != white) continue;
            if (pieceMoveMap.get(piece).isEmpty()) continue;
            selected = true;
        }

        return piece.getDiceChessId();
    }

    private void trainAgent(boolean white, Experience whiteExp, Experience blackExp) throws Exception {
        QValues qValues;

        if (white) {
            whiteExp = calculateReward(whiteExp, blackExp,true);
            qValues = calculateQValues(true, whiteExp);
            whiteAgent.backwardPropagation(qValues, whiteExp);
            return;
        }
        blackExp = calculateReward(whiteExp, blackExp,false);
        qValues = calculateQValues(false, blackExp);
        blackAgent.backwardPropagation(qValues, blackExp);
    }

    private void endTrainAgents(boolean whiteWon, Experience whiteExp, Experience blackExp) throws Exception {

        if (whiteWon) {
            whiteAgent.backwardPropagation(winQValues, whiteExp);
            blackAgent.backwardPropagation(lossQValues, blackExp);
            return;
        }
        whiteAgent.backwardPropagation(lossQValues, whiteExp);
        blackAgent.backwardPropagation(winQValues, blackExp);
    }

    private Experience calculateReward(Experience whiteExp, Experience blackExp, boolean white){

        Piece whiteCapture = whiteExp.action().capture(),
              blackCapture = blackExp.action().capture();

        if (whiteCapture == null && blackCapture == null) return whiteExp.setReward(LIVING_COST);

        if (white){
            if (whiteCapture == null) return whiteExp.setReward(LIVING_COST - blackCapture.getValue());
            if (blackCapture == null) return whiteExp.setReward(LIVING_COST + whiteCapture.getValue() );
            return whiteExp.setReward(LIVING_COST + whiteCapture.getValue() - blackCapture.getValue());
        }
        if (whiteCapture == null) return whiteExp.setReward(LIVING_COST + blackCapture.getValue());
        if (blackCapture == null) return whiteExp.setReward(LIVING_COST - whiteCapture.getValue() );
        return whiteExp.setReward(LIVING_COST + blackCapture.getValue() - whiteCapture.getValue());
    }

    private QValues calculateQValues(boolean white, Experience lastExp) throws Exception {

        double pieceQValue, moveQValue;
        QValues nextStateQValues = nextStateQValues(white);

        pieceQValue = lastExp.reward() + (gamma.valueDbl * nextStateQValues.piece());
        moveQValue = lastExp.reward() + (gamma.valueDbl * nextStateQValues.move());

        return new QValues(pieceQValue, moveQValue);
    }

    private QValues nextStateQValues(boolean white) throws Exception {

        HashMap<Piece, ArrayList<Move>> pieceMoveMap = new HashMap<>();
        ArrayList<Piece> pieces = gameState.getBoard().getAllPieces();
        Board board = gameState.getBoard();
        Input input;
        Action currentAction;
        double bestActionValue = Double.NEGATIVE_INFINITY;
        QValues bestQValue = null;

        for (Piece piece : pieces)
            pieceMoveMap.put(piece, piece.getPossibleMoves(board));

        for (int pieceID=1; pieceID < 7; pieceID++) {
            input = getInput(white, pieceID, pieceMoveMap, pieces);

            if (input.legalPieces().isEmpty()) continue;

            currentAction = getAction(input, white);

            if (currentAction.actionValue() < bestActionValue) continue;

            bestActionValue = currentAction.actionValue();
            bestQValue = new QValues(currentAction.pieceValue(), currentAction.moveValue());
        }

        return bestQValue;
    }

    private Action getAction(Input input, boolean white) throws Exception {
        if (white) return whiteAgent.predictMove(input).action();
        return blackAgent.predictMove(input).action();
    }

    private Input getInput(boolean white, int pieceID, HashMap<Piece, ArrayList<Move>> pieceMoveMap, ArrayList<Piece> pieces) throws Exception {

        double[][][] state = new double[inputChannels.valueInt][8][8];
        int channel;

        for (Piece piece : pieces) {
            channel = piece.getDiceChessId() - 1;
            channel = piece.getWhiteStatus() ? channel : channel + 6;
            state[channel][piece.getRow()][piece.getCol()] = 1;
        }

        ArrayList<Piece> legalPieces = getLegalPieces(white, pieceID, pieceMoveMap),
                         enemyPieces = getEnemyPieces(white);

        state[12] = getLegalPiecePositions(legalPieces, pieceMoveMap);
        state[13] = getLegalMovePositions(legalPieces, pieceMoveMap);
        state[14] = getLegalMovePositions(enemyPieces, pieceMoveMap);

        if (isZeroMatrix(state[14]))
            throw new Exception("No legal Pieces");

        return new Input(state, pieceID, legalPieces, pieceMoveMap);
    }

    private ArrayList<Piece> getLegalPieces(boolean white, int pieceID, HashMap<Piece, ArrayList<Move>> pieceMoveMap) throws Exception {
        ArrayList<Piece> teamPieces = white ? gameState.getBoard().getWhitePieces() : gameState.getBoard().getBlackPieces(),
                         legalPieces = new ArrayList<>();

        for (Piece piece : teamPieces) {
            if (piece.getDiceChessId() == pieceID && !pieceMoveMap.get(piece).isEmpty())
                legalPieces.add(piece);
        }

/*        if (legalPieces.isEmpty())
            throw new Exception("No Legal Pieces");*/

        return legalPieces;
    }

    private ArrayList<Piece> getEnemyPieces(boolean white){
        return white ? gameState.getBoard().getBlackPieces() : gameState.getBoard().getWhitePieces();
    }

    private double[][] getLegalMovePositions(ArrayList<Piece> pieces, HashMap<Piece, ArrayList<Move>> pieceMoveMap) {
        ArrayList<Move> moves;
        double[][] out = new double[8][8];

        for (Piece piece : pieces) {
            moves = pieceMoveMap.get(piece);
            for (Move move : moves)
                out[move.destination().getRow()][move.destination().getCol()] = 1;
        }

        return out;
    }

    private double[][] getLegalPiecePositions(ArrayList<Piece> pieces, HashMap<Piece, ArrayList<Move>> pieceMoveMap) {

        double[][] out = new double[8][8];

        for (Piece piece : pieces)
            out[piece.getRow()][piece.getCol()] = 1;

        return out;
    }

    private void printState(double[][][] state){
        try {
            for (double[][] channel : state) {
                for (double[] row : channel) {
                    System.out.println(Arrays.toString(row));
                }
                System.out.println("----------------------------------------");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) throws Exception {
        new Training();
    }

    private final QValues winQValues = new QValues(KING_CAPTURE_VALUE, KING_CAPTURE_VALUE);
    private final QValues lossQValues = new QValues(-KING_CAPTURE_VALUE, -KING_CAPTURE_VALUE);
}
