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


    private double[][][] getState(){
        Piece piece;
        int channel;

        Square[][] board = gameState.getBoard().getMBoard();
        double[][][] state = new double[14][8][8];

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

        return state;
    }

    /**
     * @throws IllegalStateException - if piece ID isn't legal
     * @param piece - piece to be added to state array
     * @return channel corresponding piece and team
     */
    private int getChannelByPiece(Piece piece){

        char id = piece.getCharId();
        int channel;

        switch (id){
            case 'P':
                channel = 0;
                break;
            case 'N':
                channel = 1;
                break;
            case 'B':
                channel = 2;
                break;
            case 'R':
                channel = 3;
                break;
            case 'Q':
                channel = 4;
                break;
            case 'K':
                channel = 5;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + id);
        }

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
                out[move.getDestination().getRow()][move.getDestination().getCol()] = 1;
        }

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
