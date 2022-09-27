package com.group4.dicechess;

import java.util.ArrayList;
import com.badlogic.gdx.Game;

public class GameState {
    private Board board;
    private boolean isPlayerWhite;
    private Move currentMove;
    private Square currentSquare;
    private ArrayList<Move> moveRecord = new ArrayList<Move>();
    private boolean whiteWin;
    private boolean onGoing;
    private boolean draw;
    private boolean forefitWhite;
    private static int count = 1;

    public GameState(Board board){
        this.board = new Board();
        isPlayerWhite = true;
        board.printBoard();
    }

    public void setSquare(Square currentSquare){
        this.currentSquare = currentSquare;
    }

    public void makeMove(int oldRow, int oldColumn, int newRow, int newColumn, Piece newPiece){
        board.setSquare(newRow, newColumn, newPiece);
        board.zeroSquare(oldRow, oldColumn);
         
        if(count%2 != 0) {
            isPlayerWhite = true;
            System.out.println("White Turn");
        } else {
            isPlayerWhite = false;
            System.out.println("Black Turn");
        }

        board.printBoard();
        
        count++;
    }   

}
