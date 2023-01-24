package com.group4.dicechess.agents.NN_Evaluation;

import com.group4.dicechess.GameState;
import com.group4.dicechess.Representation.Board;
import com.group4.dicechess.Representation.Piece;
import com.group4.dicechess.agents.NN_Evaluation.network.FC_Network;

import java.util.ArrayList;
import java.util.Arrays;

import static com.group4.dicechess.agents.NN_Evaluation.network.FC_Loader.load_network;

public class NN_Evaluation {

    private final static FC_Network network = new FC_Network();
    private static int instances = 0;

    public NN_Evaluation() {

        if (instances++ == 0)
            load_network(network);

        //System.out.println(evaluate(new GameState().getBoard()));
    }

    // Call this method to evaluate a given state.
    public static double evaluate(Board board, boolean whitesTurn){
        return network.forward(get_state_vector(board, whitesTurn));
    }

    private static double[] get_state_vector(Board board, boolean whitesTurn){

        double[] state = new double[768];
        ArrayList<Piece> pieces = board.getAllPieces();
        int pieceID, row, col, index;

        for (Piece piece : pieces) {
            pieceID = piece.getDiceChessId();

            row = piece.getRow();
            col = piece.getCol();

            // Puts white rook in (0,0)
            if (whitesTurn)
                row = Math.abs(row - 7);

            index = get_start_index(pieceID, piece.getWhiteStatus());
            index += row * 8 + col;

            state[index] = 1;
        }
        return state;
    }

    private static int get_start_index(int pieceID, boolean white){
        int index = white ? 0 : 384;
        return index + (pieceID - 1) * 64;
    }


    public static void main(String[] args) throws Exception {
        new NN_Evaluation();
    }
}
