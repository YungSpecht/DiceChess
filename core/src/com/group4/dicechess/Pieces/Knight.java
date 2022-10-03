package com.group4.dicechess.Pieces;

import java.util.ArrayList;

import com.group4.dicechess.Board;
import com.group4.dicechess.Piece;
import com.group4.dicechess.Square;

public class Knight extends Piece{
    private boolean nullStatus = false;

    public Knight(boolean whiteStatus){
        this.setWhiteStatus(whiteStatus);
        this.setId("N");
    }

    @Override
    public boolean isMoveLegal(Board board, int currentRow, int currentColumn, int newRow, int newColumn, Piece newPiece) {
        System.out.println("illegal knight");
        return true;
    }

    @Override
    public ArrayList<Square> getPossibleMoves(Board board, Square currentSquare) {
        ArrayList<Square> result = new ArrayList<Square>();
        int row = currentSquare.getRow();
        int col = currentSquare.getColumn();
        if(canCapture(board, row-1, col-2, this.getWhiteStatus()) || SquareFree(board, row-1, col-2)){
            result.add(board.getSquare(row-1, col-2));
        }
        if(canCapture(board, row+1, col-2, this.getWhiteStatus()) || SquareFree(board, row+1, col-2)){
            result.add(board.getSquare(row+1, col-2));
        }
        if(canCapture(board, row-1, col+2, this.getWhiteStatus()) || SquareFree(board, row, col-2)){
            result.add(board.getSquare(row-1, col+2));
        }
        if(canCapture(board, row+1, col+2, this.getWhiteStatus()) || SquareFree(board, row, col-2)){
            result.add(board.getSquare(row+1, col+2));
        }
        if(canCapture(board, row+2, col+1, this.getWhiteStatus()) || SquareFree(board, row, col-2)){
            result.add(board.getSquare(row+2, col+1));
        }
        if(canCapture(board, row+2, col-1, this.getWhiteStatus()) || SquareFree(board, row, col-2)){
            result.add(board.getSquare(row+2, col-1));
        }
        if(canCapture(board, row-2, col+1, this.getWhiteStatus()) || SquareFree(board, row, col-2)){
            result.add(board.getSquare(row-2, col+1));
        }
        if(canCapture(board, row-2, col-1, this.getWhiteStatus()) || SquareFree(board, row, col-2)){
            result.add(board.getSquare(row-2, col-1));
        }
        return result;
    }


    
    
}
