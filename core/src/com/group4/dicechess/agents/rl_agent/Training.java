package com.group4.dicechess.agents.rl_agent;

import com.group4.dicechess.GameState;
import com.group4.dicechess.Representation.Move;
import com.group4.dicechess.Representation.Piece;
import com.group4.dicechess.Representation.Square;

import java.util.ArrayList;
import java.util.Arrays;

/*| 1 | Pawn   |
  | 2 | Knight |
  | 3 | Bishop |
  | 4 | Rook   |
  | 5 | Queen  |
  | 6 | King   |*/




public class Training {

    private final GameState gameState;
    private double[][][] state;

    public Training(int numGames){
        gameState = new GameState();


        GameLoop();
    }


    private void GameLoop(){
        Square[][] board = gameState.getBoard().getMBoard();
        state = getState();
        printState();
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
        double[][][] state = new double[15][8][8];

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
        for (double[][] channel : state) {
            for (double[] row : channel) {
                System.out.println(Arrays.toString(row));
            }
            System.out.println("----------------------------------------");
        }
    }


    public static void main(String[] args) {
        new Training(1);
    }
}
