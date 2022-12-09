package com.group4.dicechess.agents.rl_agent;

import com.group4.dicechess.GameState;
import com.group4.dicechess.Pieces.King;
import com.group4.dicechess.Representation.Piece;
import com.group4.dicechess.agents.rl_agent.utils.Action;
import com.group4.dicechess.agents.rl_agent.utils.Experience;
import com.group4.dicechess.agents.rl_agent.utils.Input;
import com.group4.dicechess.agents.rl_agent.utils.QValues;

import java.util.Arrays;

import static com.group4.dicechess.agents.rl_agent.network.NetworkParameters.gamma;
import static com.group4.dicechess.agents.rl_agent.utils.Utils.print;
import static com.group4.dicechess.agents.rl_agent.utils.Utils.printTurn;

public class Training {

    private final GameState gameState;
    private final RL_State rl_state;

    private final RL_Agent whiteAgent, blackAgent;

    private final double LIVING_COST = -0.1;
    private final int MOVE_LIMIT = 100;
    private final int KING_CAPTURE_VALUE = 10;
    public static int totalMoves;
    private final int numGames = 50;

    public Training() throws Exception {
        gameState = new GameState();

        whiteAgent = new RL_Agent(true);
        blackAgent = new RL_Agent(false);

        rl_state = new RL_State(gameState);

        //whiteAgent.loadAgentWeights();
        //blackAgent.loadAgentWeights();

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

            if (gameOver(whiteExp)){
                endTrainAgents(true, whiteExp, blackExp);
                return;
            }

            gameState.movePiece(whiteExp.action().move());
            print("End Turn: White");

            //trainAgent(false, whiteExp, blackExp);
            blackExp = decide(false);

            if (gameOver(blackExp)){
                endTrainAgents(false, whiteExp, blackExp);
                return;
            }

            gameState.movePiece(blackExp.action().move());
            print("End Turn: Black");
        }
    }

    private Experience decide(boolean white) throws Exception {

        Input input = rl_state.getInput(white);

        printTurn(input.pieceId(), gameState);

        if (white) return whiteAgent.predictMove(input);
        return blackAgent.predictMove(input);
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

        Input input;
        Action currentAction;
        double bestActionValue = Double.NEGATIVE_INFINITY;
        QValues bestQValue = null;

        for (int pieceID=1; pieceID < 7; pieceID++) {
            input = rl_state.getInput(white, pieceID);

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

    private boolean gameOver(Experience experience) {
        return experience.action().capture() instanceof King;
    }


    public static void main(String[] args) throws Exception {
        new Training();
    }

    private final QValues winQValues = new QValues(KING_CAPTURE_VALUE, KING_CAPTURE_VALUE);
    private final QValues lossQValues = new QValues(-KING_CAPTURE_VALUE, -KING_CAPTURE_VALUE);
}
