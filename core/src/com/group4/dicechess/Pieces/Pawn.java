package com.group4.dicechess.Pieces;
import java.util.ArrayList;
import com.group4.dicechess.Board;
import com.group4.dicechess.Piece;
import com.group4.dicechess.Square;

public class Pawn extends Piece{
    private boolean isFirstMove;
    private boolean nullStatus = false;
    private int numberOfMoves;

    public Pawn(boolean whiteStatus){
        this.setWhiteStatus(whiteStatus);
        this.setId("P");
        this.numberOfMoves = 0;
        isFirstMove = true;
    }

    public void setFirstMove(boolean firstMove) {
        this.isFirstMove = firstMove;
    }

    public boolean isFirstMove(int numberOfMoves) {
        if(numberOfMoves > 0) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isMoveLegal(Board board, int currentRow, int currentColumn, int newRow, int newColumn, Piece newPiece){
        
        if(Math.abs(newColumn - currentColumn) > 0 && Math.abs(newColumn - currentColumn) < 2) {
            System.out.println("Moving diagonally");
            if(canMoveDiagonally(board, currentRow, currentColumn, newRow, newColumn, newPiece) && isLegalForward(board, currentRow, currentColumn, newRow, newColumn, newPiece)) {
                numberOfMoves++;
                return true;
            }
        } else {
            System.out.println("Moving vertically");
            if(isLegalForward(board, currentRow, currentColumn, newRow, newColumn, newPiece)) {
                numberOfMoves++;
                return true;
            }
        }

        return false;
    }


    //Checks if the piece diagonal to the pawn is of the opposing colour
    public boolean canMoveDiagonally(Board board, int currentRow, int currentColumn, int newRow, int newColumn, Piece newPiece) {

        if(!newPiece.getWhiteStatus()) {
            if(board.getPieceOfSquare(newRow, newColumn).getWhiteStatus()) {
                return true;
            }
        } else if (newPiece.getWhiteStatus()) {
            if(!board.getPieceOfSquare(newRow, newColumn).getWhiteStatus()) {
                return true;
            }
        }
 
        return false;
    }

    public boolean isLegalForward(Board board, int currentRow, int currentColumn, int newRow, int newColumn, Piece newPiece) {
        if(isFirstMove(numberOfMoves)) {
            if(board.isWhite(currentRow, currentColumn)) {
                if(currentRow - newRow > 0 && Math.abs(currentRow - newRow) <= 2  && newPiece.getNullStatus()) {
                    return true;
                }
            } else {
                if(newRow - currentRow > 0 && Math.abs(currentRow - newRow) <= 2  && newPiece.getNullStatus()) {
                    return true;
                }
            }
        } else {
            if(board.isWhite(currentRow, currentColumn)) {
                if(currentRow - newRow > 0 && Math.abs(currentRow - newRow) == 1  && newPiece.getNullStatus()) {
                    return true;
                }
            } else {
                if(newRow - currentRow > 0 && Math.abs(currentRow - newRow) == 1  && newPiece.getNullStatus()) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public ArrayList<Square> getPossibleMoves(Board board, Square currentSquare) {
        ArrayList<Square> result = new ArrayList<Square>();
        int row = currentSquare.getRow();
        int col = currentSquare.getColumn();
        int white = this.getWhiteStatus() ? 1 : 0;
        int leftBound = col - 1 < 0 ? col : col - 1;
        int rightBound = col + 1 > 7 ? col : col + 1;
        for(int i = leftBound; i <= rightBound; i++){
            if(i != col && board.getSquare(row + 1 + (white * (-2)), i).getPiece().getWhiteStatus() != this.getWhiteStatus()){
                result.add(board.getSquare(row + 1 + (white * (-2)), i));
            }
            else if(i == col && board.getSquare(row + 1 + (white * (-2)), i).getPiece().getNullStatus()){
                result.add(board.getSquare(row + 1 + (white * (-2)), i));
            }
        }
        if(isFirstMove && board.getSquare(row + 2 + (white * (-4)), col).getPiece().getNullStatus()){
            result.add(board.getSquare(row + 2 + (white * (-4)), col));
        }
        return result;
    }
}
