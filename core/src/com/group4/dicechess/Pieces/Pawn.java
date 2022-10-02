package com.group4.dicechess.Pieces;

import java.util.ArrayList;

import com.group4.dicechess.Board;
import com.group4.dicechess.Piece;
import com.group4.dicechess.Square;

public class Pawn extends Piece{
    private boolean firstMove;

    public Pawn(boolean whiteStatus){
        this.setWhiteStatus(whiteStatus);
        this.setValue("P");
        firstMove = true;
    }

    @Override
    public ArrayList<Square> getPossibleMoves(Board board, Square currentSquare) {
        ArrayList<Square> result = new ArrayList<Square>();
        int row = currentSquare.getRow();
        int col = currentSquare.getColumn();
        int white = this.getWhiteStatus() ? 1 : 0;
        for(int i = col - 1; i <= col + 1; i++){
            if(i != col && board.getSquare(row + 1 + (white * (-2)), i).getPiece().getWhiteStatus() != this.getWhiteStatus()){
                result.add(board.getSquare(row + 1 + (white * (-2)), i));
            }
            else if(i == col && board.getSquare(row + 1 + (white * (-2)), i).getPiece() == null){
                result.add(board.getSquare(row + 1 + (white * (-2)), i));
            }
        }
        if(firstMove && board.getSquare(row + 2 + (white * (-4)), col).getPiece() == null){
            result.add(board.getSquare(row + 2 + (white * (-4)), col));
        }
        return result;
    }

}
