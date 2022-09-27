package com.group4.dicechess;

import com.group4.dicechess.Pieces.King;

public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        GameState gs1 = new GameState(board);
        Piece p = new King(true);
        gs1.makeMove(7, 4, 5, 4, p);
        gs1.makeMove(7, 4, 5, 4, p);
        gs1.makeMove(7, 4, 5, 4, p);
    }
    
}
