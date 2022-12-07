package com.group4.dicechess.Pieces;

import java.util.ArrayList;

import com.group4.dicechess.Representation.Board;
import com.group4.dicechess.Representation.Move;
import com.group4.dicechess.Representation.Piece;

public class King extends Piece{


    public King(boolean whiteStatus, int currentSquareRow, int currentSquareCol){
        super(whiteStatus, 200, 6, "K", currentSquareRow, currentSquareCol);
    }

    @Override
    public ArrayList<Move> getPossibleMoves(Board board) {
        ArrayList<Move> result = new ArrayList<Move>();

        for(int i = this.getRow() -  1; i <= this.getRow() + 1; i++){
            for(int j = this.getCol() - 1; j <= this.getCol() + 1; j++){
                if(canCapture(board, i, j, this.getWhiteStatus()) || SquareFree(board, i, j)){
                    result.add(new Move(board.getSquare(this.getRow(), this.getCol()), board.getSquare(i, j), this));
                }
            }
        }

        if(this.getMoveCounter() == 0){
            if(queenside(board)){
                result.add(new Move(board.getSquare(this.getRow(), this.getCol()), board.getSquare(this.getRow(), this.getCol()-2), this));
            }
            if(kingside(board)){
                result.add(new Move(board.getSquare(this.getRow(), this.getCol()), board.getSquare(this.getRow(), this.getCol()+2), this));
            }
        }
        return result;
    }

    private boolean queenside(Board board){
        if(board.getSquare(this.getRow(), 0).getPiece() != null && board.getSquare(this.getRow(), 0).getPiece().getMoveCounter() == 0){
            for(int i = this.getCol()-1; i > 0; i--){
                if(board.getSquare(this.getRow(), i).getPiece() != null){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private boolean kingside(Board board){
        if(board.getSquare(this.getRow(), 7).getPiece() != null && board.getSquare(this.getRow(), 7).getPiece().getMoveCounter() == 0){
            for(int i = this.getCol()+1; i < 7; i++){
                if(board.getSquare(this.getRow(), i).getPiece() != null){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public Piece copy() {
        Piece copy = new King(this.getWhiteStatus(), this.getRow(), this.getCol());
        copy.setMoveCounter(this.getMoveCounter());
        return copy;
    }
    
    
}
