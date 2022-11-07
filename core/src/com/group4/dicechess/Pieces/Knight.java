package com.group4.dicechess.Pieces;

import java.util.ArrayList;

import com.group4.dicechess.Representation.Board;
import com.group4.dicechess.Representation.Piece;
import com.group4.dicechess.Representation.Square;

public class Knight extends Piece{

    public Knight(boolean whiteStatus, int currentSquareRow, int currentSquareCol){
        super(whiteStatus, 3, 2, "N", currentSquareRow, currentSquareCol);
    }

    @Override
    public ArrayList<Square> getPossibleMoves(Board board) {
        ArrayList<Square> result = new ArrayList<Square>();
        int row = this.getRow();
        int col = this.getCol();

        for(int i = col - 2; i <= col + 2; i += 4){
            for(int j = row - 1; j <= row + 1; j += 2){
                if(canCapture(board, j, i, this.getWhiteStatus()) || SquareFree(board, j, i)){
                    result.add(board.getSquare(j, i));
                }
            }
        }
        for(int i = row - 2; i <= row + 2; i += 4){
            for(int j = col - 1; j <= col + 1; j += 2){
                if(canCapture(board, i, j, this.getWhiteStatus()) || SquareFree(board, i, j)){
                    result.add(board.getSquare(i, j));
                }
            }
        }
        return result;
    }


    
    
}
