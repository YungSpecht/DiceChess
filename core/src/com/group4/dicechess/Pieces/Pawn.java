package com.group4.dicechess.Pieces;
import java.util.ArrayList;
import com.group4.dicechess.Board;
import com.group4.dicechess.Piece;
import com.group4.dicechess.Square;

public class Pawn extends Piece{
    private boolean firstMove;
    private boolean nullStatus = false;

    public Pawn(boolean whiteStatus){
        this.setWhiteStatus(whiteStatus);
        this.setValue("P");
        firstMove = true;
    }

    public void setFirstMove(boolean firstMove) {
        this.firstMove = firstMove;
    }

    public boolean isFirstMove() {
        return firstMove;
    }

    @Override
    public boolean isMoveLegal(Board board, int currentRow, int currentColumn, int newRow, int newColumn){
        
        
        if(Math.abs(newColumn- currentColumn) > 0 ) {
            if(canMoveDiagonally(board, currentRow, currentColumn, newRow, newColumn) && isLegalForward(board, currentRow, currentColumn, newRow, newColumn)) {
                return true;
            }
        } else {
            if(isLegalForward(board, currentRow, currentColumn, newRow, newColumn)) {
                return true;
            }
        }
        return false;
    }


    //Checks if the piece diagonal to the pawn is of the opposing colour
    public boolean canMoveDiagonally(Board board, int currentRow, int currentColumn, int newRow, int newColumn) {
        if(board.isWhite(currentRow, currentColumn) && board.isNull(newRow, newColumn)) {
            if(!board.isWhite(newRow, newColumn)) {
                System.out.println("White can move diagonally");
                return true;
            }
        } else if(!board.isWhite(currentRow, currentColumn) && board.isNull(newRow, newColumn)) {
            if(board.isWhite(newRow, newColumn)) {
                System.out.println("Black can move diagonally");
                return true;
            }
        } 

        System.out.println("Can't move diagonally");
        return false;
    }

    public boolean isLegalForward(Board board, int currentRow, int currentColumn, int newRow, int newColumn) {
        if(isFirstMove()) {

            if(board.isWhite(currentRow, currentColumn)) {
                if(currentRow - newRow > 0 && Math.abs(currentRow - newRow) <= 2) {
                    return true;
                }
            } else {
                if(newRow - currentRow > 0 && Math.abs(currentRow - newRow) <= 2) {
                    return true;
                }
            }
        } else {
            if(board.isWhite(currentRow, currentColumn)) {
                if(currentRow - newRow > 0 && Math.abs(currentRow - newRow) == 1) {
                    return true;
                }
            } else {
                if(newRow - currentRow > 0 && Math.abs(currentRow - newRow) == 1) {
                    return true;
                }
            }
        }
        return false;
    }

    

}
