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
            if(i != col && square.getPiece() != null && square.getPiece().getWhiteStatus() != this.getWhiteStatus()){
                result.add(square);
            }
            else if(i == col && square.getPiece() == null){
                result.add(square);
            }
        }
        Square square = board.getSquare(row + 2 + (white * (-4)), col);
        if(this.getIsFirstMove() && square.getPiece() == null){
            result.add(square);
        }
        return result;
    }
}
