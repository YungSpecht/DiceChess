package com.group4.dicechess.Pieces;
import java.util.ArrayList;

import com.group4.dicechess.Representation.Board;
import com.group4.dicechess.Representation.Move;
import com.group4.dicechess.Representation.Piece;
import com.group4.dicechess.Representation.Square;

public class Pawn extends Piece{
    
    public Pawn(boolean whiteStatus, int currentSquareRow, int currentSquareCol){
        super(whiteStatus, 100, 1, "P", currentSquareRow, currentSquareCol);
    }

    @Override
    public ArrayList<Move> getPossibleMoves(Board board) {
        ArrayList<Move> result = new ArrayList<Move>();
        int row = this.getRow();
        int col = this.getCol();
        int white = this.getWhiteStatus() ? 1 : 0;
        int leftBound = col - 1 < 0 ? col : col - 1;
        int rightBound = col + 1 > 7 ? col : col + 1;
        for(int i = leftBound; i <= rightBound; i++){
            Square destination = board.getSquare(row + 1 + (white * (-2)), i);
            if((i != col && destination.getPiece() != null && destination.getPiece().getWhiteStatus() != this.getWhiteStatus()) || enPassantPossible(board, board.getSquare(this.getRow(), this.getCol()), destination)){
                result.add(new Move(board.getSquare(this.getRow(), this.getCol()), destination, this));
                if(enPassantPossible(board, board.getSquare(this.getRow(), this.getCol()), destination)){
                    result.get(result.size()-1).setEnPassant(true);
                    result.get(result.size()-1).setCapturedPiece(board.getSquare(this.getRow(), destination.getCol()).getPiece());
                }
                else{
                    result.get(result.size()-1).setCapturedPiece(destination.getPiece());
                }
                if((this.getWhiteStatus() && destination.getRow() == 0) || (!this.getWhiteStatus() && destination.getRow() == 7)){
                    result.get(result.size()-1).setPromotion(true);
                }
            }
            else if(i == col && destination.getPiece() == null){
                result.add(new Move(board.getSquare(this.getRow(), this.getCol()), destination, this));
                if((this.getWhiteStatus() && destination.getRow() == 0) || (!this.getWhiteStatus() && destination.getRow() == 7)){
                    result.get(result.size()-1).setPromotion(true);
                }
            }
        }
        if(this.getMoveCounter() == 0){
            if(board.getSquare(row + 2 + (white * (-4)), col).getPiece() == null && board.getSquare(row + 1 + (white * (-2)), col).getPiece() == null){
                result.add(new Move(board.getSquare(this.getRow(), this.getCol()), board.getSquare(row + 2 + (white * (-4)), col), this));
            }
        }
        return result;
    }

    private boolean enPassantPossible(Board board, Square currentSquare, Square destination){
        if((this.getWhiteStatus() && currentSquare.getRow() == 3) || (!this.getWhiteStatus() && currentSquare.getRow() ==4)){
            Square sq = board.getSquare(currentSquare.getRow(), destination.getCol());
            Piece p = this.getWhiteStatus() ? board.getLastMovePieceBlack() : board.getLastMovePieceBlack();
            if(sq.getPiece() != null && sq.getPiece().getId().equals("P") && sq.getPiece() == p && sq.getPiece().getMoveCounter() == 1){
                return true;
            }
        }

/*         if(this.getWhiteStatus() && currentSquare.getRow() == 3) {
            Square sq = board.getSquare(destination.getRow()+1, destination.getCol());
            if(sq.getPiece() != null && sq.getPiece().getId().equals("P") && (sq.getPiece().getRow() == board.getLastMovePieceBlack().getRow() && sq.getPiece().getCol() == board.getLastMovePieceBlack().getCol()) && sq.getPiece().getMoveCounter() == 1){
                return true;
            }
        } else if (!this.getWhiteStatus() && currentSquare.getRow() == 4) {
            Square sq = board.getSquare(destination.getRow()-1, destination.getCol());
            if(sq.getPiece() != null && sq.getPiece().getId().equals("P") && (sq.getPiece().getRow() == board.getLastMovedPieceWhite().getRow() && sq.getPiece().getCol() == board.getLastMovedPieceWhite().getCol()) && sq.getPiece().getMoveCounter() == 1) {
                return true;
            }
        } */
        return false;
    }

    @Override
    public Piece copy() {
        Piece copy = new Pawn(this.getWhiteStatus(), this.getRow(), this.getCol());
        copy.setMoveCounter(this.getMoveCounter());
        return copy;
    }
}
