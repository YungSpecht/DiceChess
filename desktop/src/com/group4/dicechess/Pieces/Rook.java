package com.group4.dicechess.Pieces;

import com.group4.dicechess.Board;
import com.group4.dicechess.Piece;
import com.group4.dicechess.Square;

public class Rook extends Piece{

    public Rook(boolean whiteStatus){
        this.setWhiteStatus(whiteStatus);
        this.setValue(5);
    }


    @Override
    public Square[] getPossibleMoves(Board board, Square currentSquare) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
