package com.group4.dicechess;

import java.util.ArrayList;
import com.badlogic.gdx.Game;
import com.group4.dicechess.Pieces.NullPiece;

public class GameState {
    private Board board;
    private Player playerColour;
    private Move currentMove;
    private Square currentSquare;
    private ArrayList<Move> moveRecord = new ArrayList<Move>();
    private boolean whiteWin;
    private boolean onGoing;
    private boolean draw;
    private boolean forefitWhite;

    public GameState(Board board){
        this.board = new Board();
        board.printBoard();
    }

    public void setSquare(Square currentSquare){
        this.currentSquare = currentSquare;
    }

    public void makeMove(Square currentSquare, int newRow, int newColumn){
    }

}
