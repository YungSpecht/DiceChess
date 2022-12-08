package com.group4.dicechess.Representation;

public class Move {
    private Square start;
    private Square destination;
    private Piece piece;
    private Piece capturedPiece;
    private boolean enPassant;
    private boolean castling;
    private boolean promotion;
    private Piece promotedPiece;
    private int captureValue;


    public Move(Square start, Square destination, Piece piece){
        this.start = start;
        this.destination = destination;
        this.piece = piece;
        captureValue = 0;
        enPassant = false;
        castling = false;
        promotion = false;
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

    public boolean getEnPassant(){
        return enPassant;
    }

    public void setEnPassant(boolean enPassant){
        this.enPassant = enPassant;
    }

    public boolean getPromotion(){
        return promotion;
    }

    public void setPromotion(boolean promotion){
        this.promotion = promotion;
    }

    public boolean getCastling(){
        return castling;
    }

    public void setCastling(boolean castling){
        this.castling = castling;
    }

    public void setCapturedPiece(Piece capturedPiece){
        this.capturedPiece = capturedPiece;
    }

    public Piece getCapturedPiece(){
        return capturedPiece;
    }

    public void setPromotedPiece(Piece promotedPiece){
        this.promotedPiece = promotedPiece;
    }

    public Piece getPromotedPiece(){
        return promotedPiece;
    }

    public void setCaptureValue(int captureValue){
        this.captureValue = captureValue;
    }

    public int getCaptureValue(){
        return captureValue;
    }

    public Move copy(){
        return new Move(this.getStart().copy(), this.getDestination().copy(), this.getPiece().copy());
    }
}
