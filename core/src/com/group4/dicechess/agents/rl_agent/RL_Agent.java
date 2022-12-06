package com.group4.dicechess.agents.rl_agent;

import com.group4.dicechess.GameState;
import com.group4.dicechess.Representation.Move;
import com.group4.dicechess.Representation.Piece;
import com.group4.dicechess.Representation.Square;
import com.group4.dicechess.agents.rl_agent.network.Network;
import com.group4.dicechess.agents.rl_agent.utils.*;

import java.util.ArrayList;
import java.util.HashMap;

import static com.group4.dicechess.agents.rl_agent.utils.Utils.*;

public class RL_Agent {

    private final Network pieceNetwork;
    private final Network[] moveNetwork;
    private final boolean white;

    public RL_Agent(boolean isWhite){

        this.white = isWhite;

        pieceNetwork = new Network();
        moveNetwork = new Network[6];

        for (int i = 0; i < 6; i++) {
            moveNetwork[i] = new Network();
        }
    }

    public double[] predictMoveNetwork(double[][][] state, int pieceId){
        return moveNetwork[pieceId-1].forwardPropagate(state);
    }

    public Experience predictMove(Input input) throws Exception {

        double[][][] state = input.state();

        double[][] piecePrediction = unFlatten(pieceNetwork.forwardPropagate(state)),
                   movePrediction = unFlatten(predictMoveNetwork(state, input.pieceId()));

        double[][] validatedPieces = dotProduct(state[12], piecePrediction),
                   validatedMoves = dotProduct(state[13], movePrediction);

        Predictions predictions = new Predictions(piecePrediction, movePrediction, validatedPieces, validatedMoves);

        Action bestAction = findBestAction(input.legalPieces(), input.pieceMoveMap(), validatedPieces, validatedMoves);

        return new Experience  (state,
                                predictions,
                                bestAction,
                                input.pieceId(),
                         0);
    }

    private Action findBestAction(ArrayList<Piece> legalPieces, HashMap<Piece, ArrayList<Move>> pieceMoveMap, double[][] validatedPieces, double[][] validatedMoves) throws Exception {

        double  pieceValue,
                moveValue,
                actionValue,
                bestMoveValue = Double.NEGATIVE_INFINITY,
                bestPieceValue = Double.NEGATIVE_INFINITY,
                maxActionValue = Double.NEGATIVE_INFINITY;
        Move bestMove = null;
        Piece capture = null;
        ArrayList<Move> legalMoves;

        for (Piece piece : legalPieces) {

            pieceValue = validatedPieces[piece.getRow()][piece.getCol()];
            legalMoves = pieceMoveMap.get(piece);

            for (Move move : legalMoves) {
                moveValue = validatedMoves[move.destination().getRow()][move.destination().getCol()];
                actionValue = calculateActionValue(pieceValue, moveValue);

                if (actionValue < maxActionValue) continue;

                bestPieceValue = pieceValue;
                bestMoveValue = moveValue;
                maxActionValue = actionValue;
                bestMove = move;
                capture = move.destination().getPiece();
            }
        }

        // TODO: Handle this better
        if (bestMove == null)
            throw new Exception("No best move");

        return new Action(maxActionValue, bestPieceValue, bestMoveValue, bestMove, capture);
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

    public void backwardPropagation(QValues qValues, Experience experience){

        double[][] piecePrediction = experience.predictions().piecePrediction(),
                   validatedPieces = experience.predictions().validatedPieces(),
                   movePrediction = experience.predictions().movePrediction(),
                   validatedMoves = experience.predictions().validatedMoves();

        Square  start = experience.action().move().start(),
                destination = experience.action().move().destination();

        validatedPieces[start.getRow()][start.getCol()] = qValues.piece();
        validatedMoves[destination.getRow()][destination.getCol()] = qValues.move();

        pieceNetwork.backwardPropagate(flatten(validatedPieces), flatten(piecePrediction));
        moveNetwork[experience.pieceId() - 1].backwardPropagate(flatten(validatedMoves), flatten(movePrediction));
    }

}
