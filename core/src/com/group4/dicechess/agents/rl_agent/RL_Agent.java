package com.group4.dicechess.agents.rl_agent;

import com.group4.dicechess.GameState;
import com.group4.dicechess.Representation.Move;
import com.group4.dicechess.Representation.Piece;
import com.group4.dicechess.Representation.Square;
import com.group4.dicechess.agents.rl_agent.network.Network;

import java.util.ArrayList;

import static com.group4.dicechess.agents.rl_agent.Utils.*;

public class RL_Agent {

    private final Network pieceNetwork;
    private final Network[] moveNetwork;
    private final boolean white;

    private final GameState gameState;

    public RL_Agent(boolean isWhite, GameState gameState){

        this.gameState = gameState;
        this.white = isWhite;

        pieceNetwork = new Network();
        moveNetwork = new Network[6];

        for (Network network : moveNetwork) {
            network = new Network();
        }
    }

    public double[] predictMoveNetwork(double[][][] input, int pieceId){
        return moveNetwork[pieceId-1].forwardPropagate(input);
    }

    public Move predictMove(double[][][] input, int pieceId) throws Exception {

        double[][] piecePrediction = unFlatten(pieceNetwork.forwardPropagate(input));
        double[][] movePrediction = unFlatten(predictMoveNetwork(input, pieceId));

        double[][] validatedPositions = dotProduct(input[14], piecePrediction);
        double[][] validatedMoves = dotProduct((white ? input[12] : input[13]), movePrediction);

        int[] startInd = argmax2D(validatedPositions);

        Piece selected = gameState.getBoard().getPieceOnPosition(startInd[0], startInd[1]);

        ArrayList<Move> legalMoves = selected.getPossibleMoves(gameState.getBoard());

        int row = 0, col = 0;
        double moveValue,  maxMoveValue = Double.NEGATIVE_INFINITY;

        for (Move legal : legalMoves) {
            moveValue = validatedMoves[legal.destination().getRow()][legal.destination().getCol()];

            if (moveValue < maxMoveValue) continue;

            maxMoveValue = moveValue;
            row = legal.destination().getRow();
            col = legal.destination().getCol();
        }

        Square  destination = gameState.getBoard().getSquare(row, col),
                start = gameState.getBoard().getSquare(startInd[0], startInd[1]);

        
        return new Move(start, destination, selected);
    }
}
