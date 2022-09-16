package com.group4.dicechess.Pieces;

import com.group4.dicechess.Board;
import com.group4.dicechess.Piece;
import com.group4.dicechess.Square;

public class King extends Piece{

    private boolean hasCastled;

    public King(boolean whiteStatus){
        this.setWhiteStatus(whiteStatus);
        this.setValue(0);
        hasCastled = false;
    }


    @Override
    public Square[] getPossibleMoves(Board board, Square currentSquare) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
