package com.group4.dicechess.Pieces;

import java.util.ArrayList;

import com.group4.dicechess.Board;
import com.group4.dicechess.Piece;
import com.group4.dicechess.Square;

public class King extends Piece{

    private boolean nullStatus = false;
    private boolean hasCastled;

    public King(boolean whiteStatus){
        this.setWhiteStatus(whiteStatus);
        this.setValue("K");
        hasCastled = false;
    }

    @Override
    public boolean isMoveLegal(Board board, int currentRow, int currentColumn, int newRow, int newColumn, Piece newPiece) {
        System.out.println("illegal king");
        return true;
    }

    @Override
    public ArrayList<Square> getPossibleMoves(Board board, Square currentSquare) {
        // TODO Auto-generated method stub
        return null;
    }


    
    
}
