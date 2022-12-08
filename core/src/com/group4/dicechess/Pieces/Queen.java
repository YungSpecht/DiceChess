package com.group4.dicechess.Pieces;

import java.util.ArrayList;

import com.group4.dicechess.Representation.Board;
import com.group4.dicechess.Representation.Move;
import com.group4.dicechess.Representation.Piece;

public class Queen extends Piece{

    public Queen(boolean whiteStatus, int currentSquareRow, int currentSquareCol){
        super(whiteStatus, 900, 5, "Q", currentSquareRow, currentSquareCol);
    }

    @Override
    public ArrayList<Move> getPossibleMoves(Board board) {
        ArrayList<Move> result = new ArrayList<Move>();
        int row = this.getRow();
        int col = this.getCol();
        int i = 1;
        boolean downLeft = true, downRight = true, upRight = true, upLeft = true, left = true, right = true, up = true, down = true;
        while(downLeft || downRight || upRight || upLeft || down || up || left || right){
            if(left){
                if(canCapture(board, row, col - i, this.getWhiteStatus()) || SquareFree(board, row, col - i)){
                    result.add(new Move(board.getSquare(this.getRow(), this.getCol()), board.getSquare(row, col - i), this));
                    if(canCapture(board, row, col - i, this.getWhiteStatus())){
                        result.get(result.size()-1).setCapturedPiece(board.getSquare(row, col-i).getPiece());
                        left = false;
                    }
                }
                else{
                    left = false;
                }
            }
            if(right){
                if(canCapture(board, row, col + i, this.getWhiteStatus()) || SquareFree(board, row, col + i)){
                    result.add(new Move(board.getSquare(this.getRow(), this.getCol()), board.getSquare(row, col + i), this));
                    if(canCapture(board, row, col + i, this.getWhiteStatus())){
                        result.get(result.size()-1).setCapturedPiece(board.getSquare(row, col+i).getPiece());
                        right = false;
                    }
                }
                else{
                    right = false;
                }
            }
            if(up){
                if(canCapture(board, row - i, col, this.getWhiteStatus()) || SquareFree(board, row - i, col)){
                    result.add(new Move(board.getSquare(this.getRow(), this.getCol()), board.getSquare(row - i, col), this));
                    if(canCapture(board, row - i, col, this.getWhiteStatus())){
                        result.get(result.size()-1).setCapturedPiece(board.getSquare(row-i, col).getPiece());
                        up = false;
                    }
                }
                else{
                    up = false;
                }
            }
            if(down){
                if(canCapture(board, row + i, col, this.getWhiteStatus()) || SquareFree(board, row + i, col)){
                    result.add(new Move(board.getSquare(this.getRow(), this.getCol()), board.getSquare(row + i, col), this));
                    if(canCapture(board, row + i, col, this.getWhiteStatus())){
                        result.get(result.size()-1).setCapturedPiece(board.getSquare(row+i, col).getPiece());
                        down = false;
                    }
                }
                else{
                    down = false;
                }
            }
            if(downLeft){
                if(canCapture(board, row+i, col - i, this.getWhiteStatus()) || SquareFree(board, row+i, col - i)){
                    result.add(new Move(board.getSquare(this.getRow(), this.getCol()), board.getSquare(row+i, col - i), this));
                    if(canCapture(board, row+i, col - i, this.getWhiteStatus())){
                        result.get(result.size()-1).setCapturedPiece(board.getSquare(row+i, col-i).getPiece());
                        downLeft = false;
                    }
                }
                else{
                    downLeft = false;
                }
            }
            if(downRight){
                if(canCapture(board, row+i, col + i, this.getWhiteStatus()) || SquareFree(board, row+i, col + i)){
                    result.add(new Move(board.getSquare(this.getRow(), this.getCol()), board.getSquare(row+i, col + i), this));
                    if(canCapture(board, row+i, col + i, this.getWhiteStatus())){
                        result.get(result.size()-1).setCapturedPiece(board.getSquare(row+i, col+i).getPiece());
                        downRight = false;
                    }
                }
                else{
                    downRight = false;
                }
            }
            if(upRight){
                if(canCapture(board, row - i, col+i, this.getWhiteStatus()) || SquareFree(board, row - i, col+i)){
                    result.add(new Move(board.getSquare(this.getRow(), this.getCol()), board.getSquare(row - i, col+i), this));
                    if(canCapture(board, row - i, col +i, this.getWhiteStatus())){
                        result.get(result.size()-1).setCapturedPiece(board.getSquare(row-i, col+i).getPiece());
                        upRight = false;
                    }
                }
                else{
                    upRight = false;
                }
            }
            if(upLeft){
                if(canCapture(board, row - i, col-i, this.getWhiteStatus()) || SquareFree(board, row - i, col-i)){
                    result.add(new Move(board.getSquare(this.getRow(), this.getCol()), board.getSquare(row - i, col-i), this));
                    if(canCapture(board, row - i, col-i, this.getWhiteStatus())) {
                        result.get(result.size()-1).setCapturedPiece(board.getSquare(row-i, col-i).getPiece());
                        upLeft = false;
                    }
                }
                else{
                    upLeft = false;
                }
            }
            i++;
        }
        return result;
    }

    @Override
    public Piece copy() {
        Piece copy = new Queen(this.getWhiteStatus(), this.getRow(), this.getCol());
        copy.setMoveCounter(this.getMoveCounter());
        return copy;
    }

    
}
