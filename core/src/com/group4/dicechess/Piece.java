package com.group4.dicechess;

public abstract class Piece {
    private boolean whiteStatus;
    private int value;

    public void setWhiteStatus(boolean whiteStatus){
        this.whiteStatus = whiteStatus;
    }

    public void setValue(int value){
        this.value = value;
    }

    public boolean getWhiteStatus(){
        return whiteStatus;
    }

    public int getValue(){
        return value;
    }

    public abstract Square[] getPossibleMoves(Board board, Square currentSquare);
}
