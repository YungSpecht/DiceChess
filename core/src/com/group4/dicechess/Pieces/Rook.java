package com.group4.dicechess.Pieces;

import java.util.ArrayList;

import com.group4.dicechess.Board;
import com.group4.dicechess.Piece;
import com.group4.dicechess.Square;

public class Rook extends Piece{

    public Rook(boolean whiteStatus){
        this.setWhiteStatus(whiteStatus);
        this.setValue(5);
    }


    @Override
    public ArrayList<Square> getPossibleMoves(Board board, Square currentSquare) {
        ArrayList<Square> result = new ArrayList<Square>();
        int row = currentSquare.getRow();
        int col = currentSquare.getColumn();
        int i = 1;
        boolean left = true, right = true, up = true, down = true;
        while(left || right || up || down){
            if(left){
                if(canCapture(board, row, col - i, this.getWhiteStatus()) || SquareFree(board, row, col - i)){
                    result.add(board.getSquare(row, col - i));
                    if(canCapture(board, row, col - i, this.getWhiteStatus())) left = false;
                }
            }
            if(right){
                if(canCapture(board, row, col + i, this.getWhiteStatus()) || SquareFree(board, row, col + i)){
                    result.add(board.getSquare(row, col + i));
                    if(canCapture(board, row, col + i, this.getWhiteStatus())) right = false;
                }
            }
            if(up){
                if(canCapture(board, row - i, col, this.getWhiteStatus()) || SquareFree(board, row - i, col)){
                    result.add(board.getSquare(row - i, col));
                    if(canCapture(board, row - i, col, this.getWhiteStatus())) up = false;
                }
            }
            if(down){
                if(canCapture(board, row + i, col, this.getWhiteStatus()) || SquareFree(board, row + i, col)){
                    result.add(board.getSquare(row + i, col));
                    if(canCapture(board, row + i, col, this.getWhiteStatus())) down = false;
                }
            }
            i++;
        }
        return result;
    }
    

    
}
