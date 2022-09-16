package com.group4.dicechess.Pieces;

import com.group4.dicechess.Board;
import com.group4.dicechess.Piece;
import com.group4.dicechess.Square;

public class Pawn extends Piece{
    private boolean firstMove;

    public Pawn(boolean whiteStatus){
        this.setWhiteStatus(whiteStatus);
        this.setValue(1);
        firstMove = true;
    }

    @Override
    public Square[] getPossibleMoves(Board board, Square currentSquare) {
        // TODO Auto-generated method stub
        return null;
    }

    
}
