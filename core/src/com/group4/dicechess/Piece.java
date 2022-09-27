package com.group4.dicechess;

import java.util.ArrayList;

public abstract class Piece {
    private boolean whiteStatus;
    private String value;

    public void setWhiteStatus(boolean whiteStatus){
        this.whiteStatus = whiteStatus;
    }

    public void setValue(String value){
        this.value = value;
    }

    public boolean getWhiteStatus(){
        return whiteStatus;
    }

    public String getValue(){
        return value;
    }

    public static boolean SquareFree(Board board, int row, int column){
        if((row < 0 || row > 7) || (column < 0 || row > 7)) return false;
        if(board.getSquare(row, column).getPiece() == null){
            return true;
        }
        return false;
    }

    public static boolean canCapture(Board board, int row, int column, boolean white){
        if((row < 0 || row > 7) || (column < 0 || row > 7)) return false;
        if(board.getSquare(row, column).getPiece().getWhiteStatus() != white){
            return true;
        }
        return false;
    }

    /**
     * Finds all the legal moves a piece can do given its current position.
     * 
     * @param board represents the board in its current state
     * @param currentSquare represents the Square the piece is currently positioned on
     * @return ArrayList containing all the Squares the piece can legally move to
     */
    public abstract ArrayList<Square> getPossibleMoves(Board board, Square currentSquare);
}
