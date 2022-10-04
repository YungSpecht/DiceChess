package com.group4.dicechess;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import com.group4.dicechess.Representation.Board;
import com.group4.dicechess.Representation.Piece;
import com.group4.dicechess.Representation.Square;


public class Game {
    private Board board;
    private int turnCounter;
    private boolean gameOver;
    private int whiteScore;
    private int blackScore;
    private int diceRoll;
    private Random rand;
    private ArrayList<ArrayList<Square>> moveList;
    private ArrayList<Piece> legalPieces;
    private Scanner in;

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
        rand = new Random();
        moveList = new ArrayList<ArrayList<Square>>();
        legalPieces = new ArrayList<Piece>();
    }

    /**
     * The method containing the main logic of the game. Players take turns until the king is captured.
     */
    public void startGame(){
        board.printBoard();
        while(!gameOver){
            diceRoll = rand.nextInt(5) + 1;
            System.out.println("The dice has rolled a " + diceRoll + "!");

            if(turnCounter % 2 == 0){
                turn(true);
            }
            else{
                turn(false);
            }

            turnCounter++;
            board.printBoard();
            gameOver = gameIsOver();
        }
    }
    /**
     * Responsible for the control flow of each players turn. Checks whether there are any moves
     * possible for the dice roll of the player and if so lets him select a piece to move and make
     * a move with it.
     *
     * @param white indicates whether it is white to play or black
     */
    private void turn(boolean white){
        boolean movesPossible = generatePossibleMoves(white);
        if(movesPossible){
            playerMoveSelection(white);
        }
        else{
            System.out.println("There are no possible moves for your dice roll");
        }
    }

    /**
     * Populates the moveList ArrayList with all possible moves for all the pieces the user can move
     * based on which number the dice has rolled.
     *
     * @param white indicates whether it is white to play or black
     * @return true if the player can make any legal moves, false otherwise
     */
    private boolean generatePossibleMoves(boolean white){
        legalPieces.clear();
        ArrayList<Piece> allPieces = white ? board.getWhitePieces() : board.getBlackPieces();
        for(Piece piece : allPieces){
            if(piece.getDiceChessId() == diceRoll){
                legalPieces.add(piece);
            }
        }
        moveList.clear();
        for(int i = 0; i < legalPieces.size(); i++){
            Piece piece = legalPieces.get(i);
            moveList.add(piece.getPossibleMoves(board, board.getSquare(piece.getRow(), piece.getCol())));
        }

        for(ArrayList<Square> list : moveList){
            if(list.size() > 0){
                return true;
            }
        }
        return false;
    }

    /**
     * Asks the user for the piece he wants to move and which move he wants to make
     *
     * @param white indicates whether it is white to play or black
     */
    private void playerMoveSelection(boolean white){
        boolean invalidChoice = false;
        ArrayList<Square> possibleMoves = new ArrayList<Square>();
        int startRow = -1;
        int startCol = -1;
        do{
            if(invalidChoice){
                System.out.println("The square you selected doesn't contain a white Piece, is out of bounds or can't move.");
            }
            String sub = white ? " whites' " : " blacks' ";
            System.out.println("It's" + sub + "Turn!\nPlease enter the row and column of the piece you want to move.\nrow: ");
            startRow = in.nextInt();
            System.out.println("col: ");
            startCol = in.nextInt();
            Square selectedSquare = board.getSquare(startRow, startCol);
            if(selectedSquare.getPiece()!= null && selectedSquare.getPiece().getWhiteStatus() == white && selectedSquare.getPiece().getDiceChessId() == diceRoll){
                possibleMoves = getMovesForPiece(selectedSquare.getPiece());
                invalidChoice = false;
                if(possibleMoves.size() == 0){invalidChoice = true;}
            }
            else{invalidChoice = true;}
        }while(invalidChoice);

        System.out.println("Possible Squares to move to:");
        for(int i = 0; i < possibleMoves.size(); i++){
            Square square = possibleMoves.get(i);
            System.out.println("Row: " + square.getRow() + "|| Col: " + square.getCol());
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
        if(white){
            whiteScore = board.movePiece(board.getSquare(startRow, startCol), board.getSquare(destinationRow, destinationCol), possibleMoves);
        }
        else{
            blackScore = board.movePiece(board.getSquare(startRow, startCol), board.getSquare(destinationRow, destinationCol), possibleMoves);
        }
    }

    /**
     * Picks the correct ArrayList of legal moves, that was precomputed earlier, for the
     * piece the user has selected.
     *
     * @param piece the piece that the user selected to move
     * @return the ArrayList containing all the legal moves for the selected piece
     */
    private ArrayList<Square> getMovesForPiece(Piece piece){
        int index = legalPieces.indexOf(piece);
        return moveList.get(index);
    }

    /**
     * Checks whether the game is over by checking whether the king has been captured
     *
     * @return true if the game is over, false if otherwise
     */
    private boolean gameIsOver(){
        ArrayList<Piece> white = board.getWhiteCaptured();
        ArrayList<Piece> black = board.getBlackCaptured();
        for(int i = 0; i < white.size(); i++){
            if(white.get(i).getId().equals("K") || black.get(i).getId().equals("K")){
                return true;
            }
        }
        return false;
    }

    public int getTurn(){
        return turnCounter;
    }

    public int getWhiteScore(){
        return whiteScore;
    }

    public int getBlackScore(){
        return blackScore;
    }
}