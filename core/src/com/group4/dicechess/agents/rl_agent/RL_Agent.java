package com.group4.dicechess.agents.rl_agent;

import com.group4.dicechess.GameState;
import com.group4.dicechess.Representation.Move;
import com.group4.dicechess.Representation.Piece;
import com.group4.dicechess.agents.rl_agent.network.Network;
import com.group4.dicechess.agents.rl_agent.utils.Action;
import com.group4.dicechess.agents.rl_agent.utils.Experience;

import java.util.ArrayList;

import static com.group4.dicechess.agents.rl_agent.utils.Utils.*;

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

        for (int i = 0; i < 6; i++) {
            moveNetwork[i] = new Network();
        }
    }

    public double[] predictMoveNetwork(double[][][] input, int pieceId){
        return moveNetwork[pieceId-1].forwardPropagate(input);
    }

    public Experience predictMove(double[][][] input, int pieceId) throws Exception {

        double[][] piecePrediction = unFlatten(pieceNetwork.forwardPropagate(input));
        double[][] movePrediction = unFlatten(predictMoveNetwork(input, pieceId));

        // Dot product of the predicted piece to move matrix with the binary matrix of movable pieces.
        double[][] validatedPieces = dotProduct(input[14], piecePrediction);
        // Dot product of the predicted move positions with binary matrix of legal positions to move to.
        double[][] validatedMoves = dotProduct((white ? input[12] : input[13]), movePrediction);

        ArrayList<Piece> legalPieces = findLegalPieces(validatedPieces);

        Action bestAction = findBestAction(legalPieces, validatedPieces, validatedMoves);

        return new Experience  (piecePrediction,
                                movePrediction,
                                validatedPieces,
                                validatedMoves,
                                bestAction,
                                pieceId,
                         0);
    }

    private Action findBestAction(ArrayList<Piece> legalPieces, double[][] validatedPieces, double[][] validatedMoves) throws Exception {

        double pieceValue, moveValue, actionValue, maxActionValue = Double.NEGATIVE_INFINITY;
        Move bestMove = null;
        Piece capture = null;
        ArrayList<Move> legalMoves;

        for (Piece piece : legalPieces) {

            pieceValue = validatedPieces[piece.getRow()][piece.getCol()];
            legalMoves = piece.getPossibleMoves(gameState.getBoard());

            for (Move move : legalMoves) {
                moveValue = validatedMoves[move.destination().getRow()][move.destination().getCol()];
                actionValue = calculateActionValue(pieceValue, moveValue);

                if (actionValue < maxActionValue) continue;

                maxActionValue = actionValue;
                bestMove = move;
                capture = move.destination().getPiece();
            }
        }

        return new Action(maxActionValue, bestMove, capture);
    }

    private ArrayList<Piece> findLegalPieces(double[][] validatedPieces) throws Exception {

        ArrayList<Piece> legalPieces = new ArrayList<>();

        for (int i = 0; i < validatedPieces.length; i++) {
            for (int j = 0; j < validatedPieces[0].length; j++) {

                if (validatedPieces[i][j] == 0) continue;

                legalPieces.add(gameState.getBoard().getPieceOnPosition(i, j));
            }
        }

        return legalPieces;
    }

    private double calculateActionValue(double pieceValue, double moveValue) throws Exception {

        if (pieceValue > 0 && moveValue > 0)
            return pieceValue * moveValue;

        if (pieceValue < 0 && moveValue < 0)
            return pieceValue * moveValue * -2;

//       the piece shouldn't be moved, but it is advantageous to have the piece in the given location
        if (pieceValue < 0 && 0 < moveValue)
            return pieceValue * moveValue;

//      # it is disadvantageous to have the piece in the given location, but the piece should be moved
        if (moveValue < 0 && 0 < pieceValue)
            return pieceValue * moveValue * 1.5;

        throw new Exception("Invalid piece or move value");
    }

}
