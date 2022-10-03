package com.group4.dicechess.Pieces;

import java.util.ArrayList;

import com.group4.dicechess.Board;
import com.group4.dicechess.Piece;
import com.group4.dicechess.Square;

public class Rook extends Piece{

    public Rook(boolean whiteStatus){
        this.setWhiteStatus(whiteStatus);
        this.setValue("R");
    }

    @Override
    public boolean isMoveLegal(Board board, int currentRow, int currentColumn, int newRow, int newColumn, Piece newPiece) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public ArrayList<Square> getPossibleMoves(Board board, Square currentSquare) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
