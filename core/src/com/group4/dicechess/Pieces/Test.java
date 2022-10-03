package com.group4.dicechess.Pieces;

import java.util.ArrayList;

import com.group4.dicechess.Board;
import com.group4.dicechess.Square;

public class Test {
    public static void main(String[] args) {
        Board testBoard = new Board();
        testBoard.printBoard();


        ArrayList<Square> result = testBoard.getPieceOfSquare(4, 4).getPossibleMoves(testBoard, testBoard.getSquare(4, 4));
        for(int i = 0; i < result.size(); i++){
            System.out.println("Row: " + result.get(i).getRow() + " || Col: " + result.get(i).getColumn());
            System.out.println("------------------------");
        }
    }
}
