package com.group4.dicechess.Pieces;

import java.util.ArrayList;

import com.group4.dicechess.Representation.Board;
import com.group4.dicechess.Representation.Move;
import com.group4.dicechess.Representation.Piece;

public class Knight extends Piece{

    public Knight(boolean whiteStatus, int currentSquareRow, int currentSquareCol){
        super(whiteStatus, 3, 2, "N", currentSquareRow, currentSquareCol);
    }

    @Override
    public ArrayList<Move> getPossibleMoves(Board board) {
        ArrayList<Move> result = new ArrayList<Move>();
        int row = this.getRow();
        int col = this.getCol();

        for(int i = col - 2; i <= col + 2; i += 4){
            for(int j = row - 1; j <= row + 1; j += 2){
                if(canCapture(board, j, i, this.getWhiteStatus()) || SquareFree(board, j, i)){
                    result.add(new Move(board.getSquare(this.getRow(), this.getCol()), board.getSquare(j, i), this));
                }
            }
        }
        for(int i = row - 2; i <= row + 2; i += 4){
            for(int j = col - 1; j <= col + 1; j += 2){
                if(canCapture(board, i, j, this.getWhiteStatus()) || SquareFree(board, i, j)){
                    result.add(new Move(board.getSquare(this.getRow(), this.getCol()), board.getSquare(i, j), this));
                }
            }
        }
        return result;
    }

    @Override
    public Piece copy() {
        Piece copy = new Knight(this.getWhiteStatus(), this.getRow(), this.getCol());
        copy.setMoveCounter(this.getMoveCounter());
        return copy;
    }


    
    
}
