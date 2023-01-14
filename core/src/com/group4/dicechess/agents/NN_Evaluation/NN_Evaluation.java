package com.group4.dicechess.agents.NN_Evaluation;

import com.group4.dicechess.GameState;
import com.group4.dicechess.Representation.Board;
import com.group4.dicechess.Representation.Piece;
import com.group4.dicechess.agents.NN_Evaluation.network.FC_Network;

import java.util.ArrayList;
import java.util.Arrays;

import static com.group4.dicechess.agents.NN_Evaluation.network.FC_Loader.load_network;

public class NN_Evaluation {

    private final FC_Network network;

    public NN_Evaluation(GameState gameState) throws Exception {
        network = new FC_Network();
        load_network(network);

        //System.out.println(evaluate(new GameState().getBoard()));
    }

    // Call this method to evaluate a given state.
    public double evaluate(Board board){
        return network.forward(get_state_vector(board));
    }

    private double[] get_state_vector(Board board){

        double[] state = new double[768];
        ArrayList<Piece> pieces = board.getAllPieces();
        int pieceID, row, col, index;

        for (Piece piece : pieces) {
            pieceID = piece.getDiceChessId();

            row = piece.getRow();
            col = piece.getCol();

            // Puts white rook in (0,0)
            row = Math.abs(row - 7);

            index = get_start_index(pieceID, piece.getWhiteStatus());
            index += row * 8 + col;

            state[index] = 1;
        }
        return state;
    }

    private int get_start_index(int pieceID, boolean white){
        int index = white ? 0 : 384;
        return index + (pieceID - 1) * 64;
    }


    public static void main(String[] args) throws Exception {
        new NN_Evaluation(new GameState());
    }
}
