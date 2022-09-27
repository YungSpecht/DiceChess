package com.group4.dicechess.Pieces;

import java.util.ArrayList;

import com.badlogic.gdx.utils.Null;
import com.group4.dicechess.Board;
import com.group4.dicechess.Piece;
import com.group4.dicechess.Square;

public class NullPiece extends Piece{
    
    public NullPiece(){
        this.setValue(0);
    }

    @Override
    public ArrayList<Square> getPossibleMoves(Board board, Square currentSquare) {
        // TODO Auto-generated method stub
        return null;
    }

    
}
