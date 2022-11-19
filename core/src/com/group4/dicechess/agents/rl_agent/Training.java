package com.group4.dicechess.agents.rl_agent;

import com.group4.dicechess.GameState;
import com.group4.dicechess.Representation.Move;
import com.group4.dicechess.Representation.Piece;
import com.group4.dicechess.Representation.Square;
import com.group4.dicechess.agents.rl_agent.utils.Experience;

import java.util.ArrayList;
import java.util.Arrays;

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

    private final ArrayList<Experience> whiteExperiences, blackExperiences;
    private final double LIVING_COST = -0.1;
    private final int MOVE_LIMIT = 50;

    public Training(int numGames) throws Exception {
        gameState = new GameState();

        whiteAgent = new RL_Agent(true, gameState);
        blackAgent = new RL_Agent(false, gameState);

        whiteExperiences = new ArrayList<>();
        blackExperiences = new ArrayList<>();

        TrainingLoop(numGames);
    }


    private void TrainingLoop(int numEpisodes) throws Exception {

        int episode = 0;

        while (episode++ < numEpisodes) {
            gameLoop();
        }
    }

    // TODO: Add backpropagation - Batch, Optimiser (Adam)?
    // TODO: Handle promotion - pick queen when possible
    // TODO: Link GUI
    private void gameLoop() throws Exception {

        Experience whiteExp, blackExp;
        int pieceId;

        gameState.getBoard().printBoard();
        gameState.turnCounter++;
        pieceId = gameState.diceRoll();
        printTurn(gameState);
        whiteExp = whiteAgent.predictMove(getState(), pieceId);
        gameState.movePiece(whiteExp.action().move());

        gameState.turnCounter++;
        pieceId = gameState.diceRoll();
        printTurn(gameState);
        blackExp = blackAgent.predictMove(getState(), pieceId);
        gameState.movePiece(blackExp.action().move());

        while (gameState.turnCounter < MOVE_LIMIT){

            whiteExperiences.add(calculateReward(whiteExp, blackExp, true));

            gameState.turnCounter++;
            pieceId = gameState.diceRoll();
            printTurn(gameState);
            whiteExp = whiteAgent.predictMove(getState(), pieceId);
            gameState.movePiece(whiteExp.action().move());

            if (gameState.gameOver()){
                whiteExperiences.add(whiteExp.setReward(100));
                return;
            }

            blackExperiences.add(calculateReward(whiteExp, blackExp, false));

            gameState.turnCounter++;
            pieceId = gameState.diceRoll();
            printTurn(gameState);
            blackExp = blackAgent.predictMove(getState(), pieceId);
            gameState.movePiece(blackExp.action().move());

            if (gameState.gameOver()){
                blackExperiences.add(blackExp.setReward(100));
                return;
            }
        }
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

    /*
State consist of 15 channels:
Channels 0-5: white pieces (e.g. [0][5][3] = 1 white pawn in position [5][3])
Channels 6-11: black pieces
Channels 12:
     */

    private double[][][] getState(){
        Piece piece;
        int channel;

        Square[][] board = gameState.getBoard().getMBoard();
        double[][][] state = new double[inputChannels.valueInt][8][8];

        // TODO just loop over pieces rather than the board
        for (Square[] row : board) {
            for (Square square : row){
                piece = square.getPiece();
                if (piece == null) continue;

                channel = getChannelByPiece(piece);
                state[channel][piece.getRow()][piece.getCol()] = 1;
            }
        }

        // TODO: verify dice roll is consistent
        if (gameState.isWhitesTurn()) {
            state[12] = getMovesBinary(true, gameState.getDiceRoll());
            state[13] = getMovesBinary(false);
        } else{
            state[12] = getMovesBinary(true);
            state[13] = getMovesBinary(false, gameState.getDiceRoll());
        }

        state[14] = getPositionBinary(gameState.isWhitesTurn(), gameState.getDiceRoll());

        return state;
    }

    /**
     * @throws IllegalStateException - if piece ID isn't legal
     * @param piece - piece to be added to state array
     * @return channel corresponding piece and team
     */
    private int getChannelByPiece(Piece piece){

        char id = piece.getCharId();
        int channel = switch (id) {
            case 'P' -> 0;
            case 'N' -> 1;
            case 'B' -> 2;
            case 'R' -> 3;
            case 'Q' -> 4;
            case 'K' -> 5;
            default -> throw new IllegalStateException("Unexpected value: " + id);
        };

        return piece.getWhiteStatus() ? channel : channel + 6;
    }

    private double[][] getMovesBinary(boolean white, int piece) {
        return createMoveBinary(gameState.getPieceMoves(white, piece));
    }

    private double[][] getMovesBinary(boolean white){
        return createMoveBinary(gameState.getTeamMoves(white));
    }

    private double[][] createMoveBinary(ArrayList<ArrayList<Move>> allMoves){
        double[][] out = new double[8][8];

        for (ArrayList<Move> moveList : allMoves) {
            for (Move move : moveList)
                out[move.destination().getRow()][move.destination().getCol()] = 1;
        }

        return out;
    }

    private double[][] getPositionBinary(boolean white, int piece){
        ArrayList<Piece> movablePieces = gameState.getMovablePieces(white, piece);
        double[][] out = new double[8][8];

        for (Piece movablePiece : movablePieces)
            out[movablePiece.getRow()][movablePiece.getCol()] = 1;

        return out;
    }

    private void printState(){
        for (double[][] channel : getState()) {
            for (double[] row : channel) {
                System.out.println(Arrays.toString(row));
            }
            System.out.println("----------------------------------------");
        }
    }


    public static void main(String[] args) throws Exception {
        new Training(50);
    }
}
