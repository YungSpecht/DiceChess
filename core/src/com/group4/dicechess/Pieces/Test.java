package com.group4.dicechess.Pieces;

import java.util.ArrayList;

import com.group4.dicechess.Board;
import com.group4.dicechess.Square;

public class Test {
    public static void main(String[] args) {
        Board testBoard = new Board();
        testBoard.printBoard();


        ArrayList<Square> result = testBoard.getPieceOfSquare(1, 7).getPossibleMoves(testBoard, testBoard.getSquare(1, 7));
        for(int i = 0; i < result.size(); i++){
            System.out.println("Row: " + result.get(i).getRow() + " || Col: " + result.get(i).getColumn());
            System.out.println("------------------------");
        }
    }
}
