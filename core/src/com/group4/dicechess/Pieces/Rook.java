package com.group4.dicechess.Pieces;

import java.util.ArrayList;

import com.group4.dicechess.Representation.Board;
import com.group4.dicechess.Representation.Piece;
import com.group4.dicechess.Representation.Square;

public class Rook extends Piece{

    public Rook(boolean whiteStatus, int currentSquareRow, int currentSquareCol){
        super(whiteStatus, 5, 4, "R", currentSquareRow, currentSquareCol);
    }

    @Override
    public ArrayList<Square> getPossibleMoves(Board board, Square currentSquare) {
        ArrayList<Square> result = new ArrayList<Square>();
        int row = currentSquare.getRow();
        int col = currentSquare.getCol();
        int i = 1;
        boolean left = true, right = true, up = true, down = true;
        while(left || right || up || down){
            if(left){
                if(canCapture(board, row, col - i, this.getWhiteStatus()) || SquareFree(board, row, col - i)){
                    result.add(board.getSquare(row, col - i));
                    if(canCapture(board, row, col - i, this.getWhiteStatus())){
                        left = false;
                    }
                }
                else{
                    left = false;
                }
            }
            if(right){
                if(canCapture(board, row, col + i, this.getWhiteStatus()) || SquareFree(board, row, col + i)){
                    result.add(board.getSquare(row, col + i));
                    if(canCapture(board, row, col + i, this.getWhiteStatus())){
                        right = false;
                    }
                }
                else{
                    right = false;
                }
            }
            if(up){
                if(canCapture(board, row - i, col, this.getWhiteStatus()) || SquareFree(board, row - i, col)){
                    result.add(board.getSquare(row - i, col));
                    if(canCapture(board, row - i, col, this.getWhiteStatus())){
                        up = false;
                    }
                }
                else{
                    up = false;
                }
            }
            if(down){
                if(canCapture(board, row + i, col, this.getWhiteStatus()) || SquareFree(board, row + i, col)){
                    result.add(board.getSquare(row + i, col));
                    if(canCapture(board, row + i, col, this.getWhiteStatus())){
                        down = false;
                    }
                }
                else{
                    down = false;
                }
            }
            i++;
        }
        return result;
    }
    
}
