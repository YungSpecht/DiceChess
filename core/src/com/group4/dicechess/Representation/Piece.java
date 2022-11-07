package com.group4.dicechess.Representation;

import java.util.ArrayList;

public abstract class Piece {
    private boolean whiteStatus;
    private int moveCounter;
    private int value;
    private int diceChessId;
    private String identifier;
    private int currentSquareRow;
    private int currentSquareCol;
    private String notation;


    public Piece(boolean whiteStatus, int value, int diceChessId, String identifier, int currentSquareRow, int currentSquareCol){
        this.whiteStatus = whiteStatus;
        this.value = value;
        this.diceChessId = diceChessId;
        this.identifier = identifier;
        this.currentSquareRow = currentSquareRow;
        this.currentSquareCol = currentSquareCol;
        this.notation = "";
        moveCounter = 0;
    }

    public void setNotation(String nt){
        this.notation = nt;
    }

    public String getNotation(){
        return this.notation;
    }

    public String getId(){
        return identifier;
    }

    public boolean getWhiteStatus(){
        return whiteStatus;
    }

    public int getMoveCounter(){
        return moveCounter;
    }

    public void increaseMoveCounter(){
        moveCounter++;
    }

    public int getValue(){
        return value;
    }

    public int getRow(){
        return currentSquareRow;
    }

    public int getCol(){
        return currentSquareCol;
    }

    public void setRow(int row){
        currentSquareRow = row;
    }

    public void setCol(int col){
        currentSquareCol = col;
    }

    public int getDiceChessId(){
        return diceChessId;
    }

    public String getIdentifier(){
        return identifier;
    }


    /**
     * Checks whether a square is empty and a piece can move to it

     * @param board represents the board in its current state
     * @param row the row of the square to be checked
     * @param col the column of the square to be checked
     * @return true if the square is free, false if it is occupied or the coordinates are out of bounds
     */
    public static boolean SquareFree(Board board, int row, int col){
        if(row < 0 || row > 7 || col < 0 || col > 7){
            return false;
        }
        if(board.getSquare(row, col).getPiece() == null){
            return true;
        }
        return false;
    }

    /**
     * Checks whether a piece can capture on anoher square of the board
     * 
     * @param board represents the board in its current state
     * @param row the row of the square to be checked for capturable pieces
     * @param col the column of the square to be checked for capturable pieces
     * @return true if the square contains a piece that can be captured, false otherwise
     */
    public static boolean canCapture(Board board, int row, int col, boolean white){
        if(row < 0 || row > 7 || col < 0 || col > 7){
            return false;
        }
        Piece piece = board.getSquare(row, col).getPiece();
        if(piece != null && piece.getWhiteStatus() != white){
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
    public abstract ArrayList<Square> getPossibleMoves(Board board);

}
