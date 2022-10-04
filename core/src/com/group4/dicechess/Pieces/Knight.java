package com.group4.dicechess.Pieces;

import java.util.ArrayList;

import com.group4.dicechess.Board;
import com.group4.dicechess.Piece;
import com.group4.dicechess.Square;

public class Knight extends Piece{

    public Knight(boolean whiteStatus, int currentSquareRow, int currentSquareCol){
        super(whiteStatus, 3, 2, "N", currentSquareRow, currentSquareCol);
    }

    @Override
    public ArrayList<Square> getPossibleMoves(Board board, Square currentSquare) {
        ArrayList<Square> result = new ArrayList<Square>();
        int row = currentSquare.getRow();
        int col = currentSquare.getCol();
        if(canCapture(board, row-1, col-2, this.getWhiteStatus()) || SquareFree(board, row-1, col-2)){
            result.add(board.getSquare(row-1, col-2));
        }
        if(canCapture(board, row+1, col-2, this.getWhiteStatus()) || SquareFree(board, row+1, col-2)){
            result.add(board.getSquare(row+1, col-2));
        }
        if(canCapture(board, row-1, col+2, this.getWhiteStatus()) || SquareFree(board, row-1, col+2)){
            result.add(board.getSquare(row-1, col+2));
        }
        if(canCapture(board, row+1, col+2, this.getWhiteStatus()) || SquareFree(board, row+1, col+2)){
            result.add(board.getSquare(row+1, col+2));
        }
        if(canCapture(board, row+2, col+1, this.getWhiteStatus()) || SquareFree(board, row+2, col+1)){
            result.add(board.getSquare(row+2, col+1));
        }
        if(canCapture(board, row+2, col-1, this.getWhiteStatus()) || SquareFree(board, row+2, col-1)){
            result.add(board.getSquare(row+2, col-1));
        }
        if(canCapture(board, row-2, col+1, this.getWhiteStatus()) || SquareFree(board, row-2, col+1)){
            result.add(board.getSquare(row-2, col+1));
        }
        if(canCapture(board, row-2, col-1, this.getWhiteStatus()) || SquareFree(board, row-2, col-1)){
            result.add(board.getSquare(row-2, col-1));
        }
        return result;
    }


    
    
}
