package com.group4.dicechess.Pieces;

import java.util.ArrayList;

import com.group4.dicechess.Board;
import com.group4.dicechess.Piece;
import com.group4.dicechess.Square;

public class Queen extends Piece{
    private boolean nullStatus = false;

    public Queen(boolean whiteStatus){
        this.setWhiteStatus(whiteStatus);
        this.setValue("Q");
    }

    @Override
    public boolean isMoveLegal(Board board, int currentRow, int currentColumn, int newRow, int newColumn) {
        System.out.println("illegal queen");
        return false;
    }

    
}
