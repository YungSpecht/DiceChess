package com.group4.dicechess;

import java.util.ArrayList;
import com.badlogic.gdx.Game;

public class GameState {
    private Board board;
    private boolean isPlayerWhite;
    private Square currentSquare;
    private static int count = 1;

    public GameState(Board board){
        this.board = new Board();
        isPlayerWhite = true;
        board.printBoard();
    }

    public void setSquare(Square currentSquare){
        this.currentSquare = currentSquare;
    }

    public void makeMove(int currentRow, int currentColumn, int newRow, int newColumn){

        if(board.getPieceOfSquare(currentRow, currentColumn).isMoveLegal(board, currentRow, currentColumn, newRow, newColumn)) {
            board.setSquare(newRow, newColumn, board.getPieceOfSquare(currentRow, currentColumn));
            board.setNullPiece(currentRow, currentColumn);

            if(count%2 != 0) {
            isPlayerWhite = true;
            System.out.println("White Turn");
        } else {
            isPlayerWhite = false;
            System.out.println("Black Turn");
        }

            board.printBoard();
        
            count++;
        } else {
            System.out.println("Illegal Move");
        }
         
        
    }   

}
