package com.group4.dicechess;

import com.group4.dicechess.Pieces.NullPiece;

public class Square {
    private int row;
    private int colulmn;
    private Piece piece;

    public Square(int row, int column, Piece piece){
        this.row = row;
        this.colulmn = column;
        this.piece = piece;
    }

    public int getRow(){
        return row;
    }

    public int getColumn(){
        return colulmn;
    }

    public void setPiece(Piece piece){
        this.piece = piece;
    }

    public Piece getPiece(){
        return piece;
    }

    public void wipeSquare(){
        this.piece = new NullPiece();
    }

    public void setNewPosition(int newRow, int newColumn){
        this.row = newRow;
        this.colulmn = newColumn;
    }
}
