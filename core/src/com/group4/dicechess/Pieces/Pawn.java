package com.group4.dicechess.Pieces;
import java.util.ArrayList;

import com.group4.dicechess.Representation.Board;
import com.group4.dicechess.Representation.Piece;
import com.group4.dicechess.Representation.Square;

public class Pawn extends Piece{
    
    public Pawn(boolean whiteStatus, int currentSquareRow, int currentSquareCol){
        super(whiteStatus, 1, 1, "P", currentSquareRow, currentSquareCol);
    }

    @Override
    public ArrayList<Square> getPossibleMoves(Board board, Square currentSquare) {
        ArrayList<Square> result = new ArrayList<Square>();
        int row = currentSquare.getRow();
        int col = currentSquare.getCol();
        int white = this.getWhiteStatus() ? 1 : 0;
        int leftBound = col - 1 < 0 ? col : col - 1;
        int rightBound = col + 1 > 7 ? col : col + 1;
        for(int i = leftBound; i <= rightBound; i++){
            Square square = board.getSquare(row + 1 + (white * (-2)), i);
            if((i != col && square.getPiece() != null && square.getPiece().getWhiteStatus() != this.getWhiteStatus()) || (enPassantPossible(board, currentSquare, square))){
                result.add(square);
            }
            else if(i == col && square.getPiece() == null){
                result.add(square);
            }
        }
        Square square = board.getSquare(row + 2 + (white * (-4)), col);
        if(this.getMoveCounter() == 0 && square.getPiece() == null){
            result.add(square);
        }
        return result;
    }

    private boolean enPassantPossible(Board board, Square currentSquare, Square square){
        if(currentSquare.getPiece().getWhiteStatus() && currentSquare.getRow() == 3 ) {
            Square sq = board.getSquare(square.getRow()+1, square.getCol());
            if(sq.getPiece() != null && sq.getPiece().getId().equals("P") && sq.getPiece() == board.getLastMovePieceBlack() && sq.getPiece().getMoveCounter() == 1){
                return true;
            }
        } else if (!currentSquare.getPiece().getWhiteStatus() && currentSquare.getRow() == 4) {
            Square sq = board.getSquare(square.getRow()-1, square.getCol());
            if(sq.getPiece() != null && sq.getPiece().getId().equals("P") && sq.getPiece() == board.getLastMovedPieceWhite() && sq.getPiece().getMoveCounter() == 1) {
                return true;
            }
        }
        return false;
    }
}
