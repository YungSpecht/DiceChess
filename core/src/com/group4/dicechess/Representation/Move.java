package com.group4.dicechess.Representation;

public class Move {
    private Square start;
    private Square destination;
    private Piece piece;

    public Move(Square start, Square destination, Piece piece){
        this.start = start;
        this.destination = destination;
        this.piece = piece;
    }

    public Square getStart(){
        return start;
    }

    public Square getDestination(){
        return destination;
    }

    public Piece getPiece(){
        return piece;
    }

    public Move copy(){
        return new Move(this.getStart().copy(), this.getDestination().copy(), this.getPiece().copy());
    }
}
