package com.group4.dicechess;

import java.util.ArrayList;
import java.util.Scanner;


public class Game {
    private Board board;
    private int turnCounter;
    private boolean gameOver;
    private int whiteScore;
    private int blackScore;
    Scanner in;

    public static void main(String[] args) {
        Game game = new Game();
        game.startGame();
    }

    public Game(){
        board = new Board();
        turnCounter = 0;
        whiteScore = 0;
        blackScore = 0;
        gameOver = false;
        in = new Scanner(System.in);
    }

    public void startGame(){
        board.printBoard();
        while(!gameOver){
            if(turnCounter % 2 == 0){
                whiteTurn();
            }
            else{
                blackTurn();
            }
            turnCounter++;
            board.printBoard();
        }
    }

    public void whiteTurn(){
        boolean invalidChoice = false;
        ArrayList<Square> possibleMoves = new ArrayList<Square>();
        int startRow = -1;
        int startCol = -1;
        do{
            if(invalidChoice){
                System.out.println("The square you selected doesn't contain a white Piece, is out of bounds or can't move.");
            }
            System.out.println("It's whites' Turn!\nPlease enter the row and column of the piece you want to move.\nrow: ");
            startRow = in.nextInt();
            System.out.println("col: ");
            startCol = in.nextInt();
            Square selectedSquare = board.getSquare(startRow, startCol);
            if(selectedSquare.getPiece()!= null && selectedSquare.getPiece().getWhiteStatus()){
                possibleMoves = selectedSquare.getPiece().getPossibleMoves(board, selectedSquare);
                invalidChoice = false;
                if(possibleMoves.size() == 0){invalidChoice = true;}
            }
            else{invalidChoice = true;}
        }while(invalidChoice);

        System.out.println("Possible Squares to move to:");
        for(int i = 0; i < possibleMoves.size(); i++){
            Square square = possibleMoves.get(i);
            System.out.println("Row: " + square.getRow() + "|| Col: " + square.getColumn());
            System.out.println("---------------------");
        }

        System.out.println("Please select a square to move to: ");
        int destinationRow = -1;
        int destinationCol = -1;
        do{
            if(invalidChoice){
                System.out.println("Please enter a square listed in the Selection!");
            }
            System.out.println("Row: ");
            destinationRow = in.nextInt();
            System.out.println("Col: ");
            destinationCol = in.nextInt();
            if(!possibleMoves.contains(board.getSquare(destinationRow, destinationCol))){
                invalidChoice = true;
            }
            else{
                invalidChoice = false;
            }
            
        }while(invalidChoice);
        whiteScore = board.movePiece(board.getSquare(startRow, startCol), board.getSquare(destinationRow, destinationCol), possibleMoves);
    }

    public void blackTurn(){
        boolean invalidChoice = false;
        ArrayList<Square> possibleMoves = new ArrayList<Square>();
        int startRow = -1;
        int startCol = -1;
        do{
            if(invalidChoice){
                System.out.println("The square you selected doesn't contain a black Piece, is out of bounds or can't move.");
            }
            System.out.println("It's blacks' Turn!\nPlease enter the row and column of the piece you want to move.\nrow: ");
            startRow = in.nextInt();
            System.out.println("col: ");
            startCol = in.nextInt();
            Square selectedSquare = board.getSquare(startRow, startCol);
            if(selectedSquare.getPiece()!= null && !selectedSquare.getPiece().getWhiteStatus()){
                possibleMoves = selectedSquare.getPiece().getPossibleMoves(board, selectedSquare);
                invalidChoice = false;
                if(possibleMoves.size() == 0){invalidChoice = true;}
            }
            else{invalidChoice = true;}
        }while(invalidChoice);

        System.out.println("Possible Squares to move to:");
        for(int i = 0; i < possibleMoves.size(); i++){
            Square square = possibleMoves.get(i);
            System.out.println("Row: " + square.getRow() + "|| Col: " + square.getColumn());
            System.out.println("---------------------");
        }

        System.out.println("Please select a square to move to: ");
        int destinationRow = -1;
        int destinationCol = -1;
        do{
            if(invalidChoice){
                System.out.println("Please enter a square listed in the Selection!");
            }
            System.out.println("Row: ");
            destinationRow = in.nextInt();
            System.out.println("Col: ");
            destinationCol = in.nextInt();
            if(!possibleMoves.contains(board.getSquare(destinationRow, destinationCol))){
                invalidChoice = true;
            }
            else{
                invalidChoice = false;
            }
            
        }while(invalidChoice);
        blackScore = board.movePiece(board.getSquare(startRow, startCol), board.getSquare(destinationRow, destinationCol), possibleMoves);
    }
}
