package com.group4.dicechess;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Board board = new Board();
        GameState gs1 = new GameState(board);
        boolean play = true;

        

        System.out.println(board.getPieceOfSquare(2, 0));

        while(play) {
            int[] moves = new int[4];
        
            System.out.println("cR and cC: ");
            moves[0] = scanner.nextInt();
            moves[1] = scanner.nextInt();

            System.out.println("nR and nC: ");
            moves[2] = scanner.nextInt();
            moves[3] = scanner.nextInt();

            gs1.makeMove(moves[0], moves[1], moves[2], moves[3]);
        }
        scanner.close();
    }
    
}
