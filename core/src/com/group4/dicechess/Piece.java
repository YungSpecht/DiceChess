package com.group4.dicechess;

import java.util.ArrayList;

public abstract class Piece {
    private boolean whiteStatus;
    private boolean nullStatus;
    private boolean isFirstMove;
    private int numberOfMoves;
    private int value;
    private String identifier;

    public void setWhiteStatus(boolean whiteStatus){
        this.whiteStatus = whiteStatus;
    }

    public void setId(String identifier){
        this.identifier = identifier;
    }

    public String getId(){
        return identifier;
    }

    public boolean getWhiteStatus(){
        return whiteStatus;
    }

    public int getValue(){
        return value;
    }

    public boolean getNullStatus() {
        return nullStatus;
    }

    public void setNullStatus(boolean nullStatus) {
        this.nullStatus = nullStatus;
    }

    public void setIsFirstMove(boolean isFirstMove) {
        this.isFirstMove = isFirstMove;
    }

    public boolean getIsFirstMove() {
        return isFirstMove;
    }

    public int getNumberOfMoves() {
        return numberOfMoves;
    }

    public static boolean SquareFree(Board board, int row, int column){
        if(row < 0 || row > 7 || column < 0 || column > 7) return false;
        if(board.getSquare(row, column).getPiece().getNullStatus()){
            return true;
        }
        return false;
    }

    public static boolean canCapture(Board board, int row, int column, boolean white){
        if(row < 0 || row > 7 || column < 0 || column > 7) return false;
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
    public abstract boolean isMoveLegal(Board board, int currentRow, int currentColumn, int newRow, int newColumn, Piece newPiece);

    
    public abstract ArrayList<Square> getPossibleMoves(Board board, Square currentSquare);
}
