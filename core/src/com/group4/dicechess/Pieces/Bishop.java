package com.group4.dicechess.Pieces;

import java.util.ArrayList;

import com.group4.dicechess.Board;
import com.group4.dicechess.Piece;
import com.group4.dicechess.Square;

public class Bishop extends Piece{

    public Bishop(boolean whiteStatus, int currentSquareRow, int currentSquareCol){
        super(whiteStatus, 3, 3, "B", currentSquareRow, currentSquareCol);
    }

    @Override
    public ArrayList<Square> getPossibleMoves(Board board, Square currentSquare) {
        ArrayList<Square> result = new ArrayList<Square>();
        int row = currentSquare.getRow();
        int col = currentSquare.getCol();
        int i = 1;
        boolean downLeft = true, downRight = true, upRight = true, upLeft = true;
        while(downLeft || downRight || upRight || upLeft){
            if(downLeft){
                if(canCapture(board, row+i, col - i, this.getWhiteStatus()) || SquareFree(board, row+i, col - i)){
                    result.add(board.getSquare(row+i, col - i));
                    if(canCapture(board, row+i, col - i, this.getWhiteStatus())) downLeft = false;
                }
                else{
                    downLeft = false;
                }
            }
            if(downRight){
                if(canCapture(board, row+i, col + i, this.getWhiteStatus()) || SquareFree(board, row+i, col + i)){
                    result.add(board.getSquare(row+i, col + i));
                    if(canCapture(board, row+i, col + i, this.getWhiteStatus())) downRight = false;
                }
                else{
                    downRight = false;
                }
            }
            if(upRight){
                if(canCapture(board, row - i, col+i, this.getWhiteStatus()) || SquareFree(board, row - i, col+i)){
                    result.add(board.getSquare(row - i, col+i));
                    if(canCapture(board, row - i, col +i, this.getWhiteStatus())) upRight = false;
                }
                else{
                    upRight = false;
                }
            }
            if(upLeft){
                if(canCapture(board, row - i, col-i, this.getWhiteStatus()) || SquareFree(board, row - i, col-i)){
                    result.add(board.getSquare(row - i, col-i));
                    if(canCapture(board, row - i, col-i, this.getWhiteStatus())) upLeft = false;
                }
                else{
                    upLeft = false;
                }
            }
            i++;
        }
        return result;
    }

    
}
