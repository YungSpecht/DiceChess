package com.group4.dicechess.Pieces;

import com.group4.dicechess.Board;
import com.group4.dicechess.Piece;

public class NullPiece extends Piece {
    private boolean nullStatus = true;

    public NullPiece(boolean nullStatus){
        this.setNullStatus(true);
        this.setValue("O");
    }

    @Override
    public boolean isMoveLegal(Board board, int currentRow, int currentColumn, int newRow, int newColumn, Piece newPiece) {
        System.out.println("illegal nullpiece");
        return true;
    }
    
}
