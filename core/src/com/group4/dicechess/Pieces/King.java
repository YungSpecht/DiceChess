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
        boolean downLeft = true, downRight = true, upRight = true, upLeft = true, left = true, right = true, up = true, down = true;
        for(int i =1; i <= 1; i++){
            if(left){
                if(canCapture(board, row, col - i, this.getWhiteStatus()) || SquareFree(board, row, col - i)){
                    result.add(board.getSquare(row, col - i));
                    if(canCapture(board, row, col - i, this.getWhiteStatus())) left = false;
                }
                else{
                    left = false;
                }
            }
            if(right){
                if(canCapture(board, row, col + i, this.getWhiteStatus()) || SquareFree(board, row, col + i)){
                    result.add(board.getSquare(row, col + i));
                    if(canCapture(board, row, col + i, this.getWhiteStatus())) right = false;
                }
                else{
                    right = false;
                }
            }
            if(up){
                if(canCapture(board, row - i, col, this.getWhiteStatus()) || SquareFree(board, row - i, col)){
                    result.add(board.getSquare(row - i, col));
                    if(canCapture(board, row - i, col, this.getWhiteStatus())) up = false;
                }
                else{
                    up = false;
                }
            }
            if(down){
                if(canCapture(board, row + i, col, this.getWhiteStatus()) || SquareFree(board, row + i, col)){
                    result.add(board.getSquare(row + i, col));
                    if(canCapture(board, row + i, col, this.getWhiteStatus())) down = false;
                }
                else{
                    down = false;
                }
            }
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
                    result.add(board.getSquare(row + i, col-i));
                    if(canCapture(board, row - i, col-i, this.getWhiteStatus())) upLeft = false;
                }
                else{
                    upLeft = false;
                }
            }
        }
        return result;
    }


    
    
}
