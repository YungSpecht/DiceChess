package com.group4.dicechess.Representation;

public class Square {
    private int row;
    private int col;
    private Piece piece;

    public Square(int row, int col, Piece piece){
        this.row = row;
        this.col = col;
        this.piece = piece;
    }

    public int getRow(){
        return row;
    }

    public int getCol(){
        return col;
    }

    public void setPiece(Piece piece){
        this.piece = piece;
    }

    public Piece getPiece(){
        return piece;
    }

    public Square copy(){
        return new Square(this.getRow(), this.getCol(), this.getPiece().copy());
    }
}
