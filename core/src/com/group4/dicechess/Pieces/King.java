package com.group4.dicechess.Pieces;

import java.util.ArrayList;

import com.group4.dicechess.Representation.Board;
import com.group4.dicechess.Representation.Piece;
import com.group4.dicechess.Representation.Square;

public class King extends Piece{


    public King(boolean whiteStatus, int currentSquareRow, int currentSquareCol){
        super(whiteStatus, 1000, 6, "K", currentSquareRow, currentSquareCol);
    }

    @Override
    public ArrayList<Square> getPossibleMoves(Board board, Square currentSquare) {
        ArrayList<Square> result = new ArrayList<Square>();
        int row = currentSquare.getRow();
        int col = currentSquare.getCol();
        if(canCapture(board, row, col - 1, this.getWhiteStatus()) || SquareFree(board, row, col - 1)){
            result.add(board.getSquare(row, col - 1));
        }
        if(canCapture(board, row, col + 1, this.getWhiteStatus()) || SquareFree(board, row, col + 1)){
            result.add(board.getSquare(row, col + 1));
        }
        if(canCapture(board, row - 1, col, this.getWhiteStatus()) || SquareFree(board, row - 1, col)){
            result.add(board.getSquare(row - 1, col));
        }
        if(canCapture(board, row + 1, col, this.getWhiteStatus()) || SquareFree(board, row + 1, col)){
            result.add(board.getSquare(row + 1, col));
        }
        if(canCapture(board, row+1, col - 1, this.getWhiteStatus()) || SquareFree(board, row+1, col - 1)){
            result.add(board.getSquare(row+1, col - 1));
        }
        if(canCapture(board, row+1, col + 1, this.getWhiteStatus()) || SquareFree(board, row+1, col + 1)){
            result.add(board.getSquare(row+1, col + 1));
        }
        if(canCapture(board, row - 1, col+1, this.getWhiteStatus()) || SquareFree(board, row - 1, col+1)){
            result.add(board.getSquare(row - 1, col+1));
        }
        if(canCapture(board, row - 1, col-1, this.getWhiteStatus()) || SquareFree(board, row - 1, col-1)){
            result.add(board.getSquare(row - 1, col-1));
        }
        return result;
        /*
        if(this.getMoveCounter() == 0){
            if(queenside(board, currentSquare)){
                result.add(board.getSquare(row, col-2));
            }
            if(kingside(board, currentSquare)){
                result.add(board.getSquare(row, col+2));
            }
        }
        */
    }

    private boolean queenside(Board board, Square currentSquare){
        int row = currentSquare.getRow();
        if(board.getSquare(row, 0).getPiece().getMoveCounter() == 0){
            for(int i = currentSquare.getCol()-1; i > 0; i--){
                if(board.getSquare(row, i) != null){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private boolean kingside(Board board, Square currentSquare){
        int row = currentSquare.getRow();
        if(board.getSquare(row, 7).getPiece().getMoveCounter() == 0){
            for(int i = currentSquare.getCol()+1; i < 7; i++){
                if(board.getSquare(row, i) != null){
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    
}
