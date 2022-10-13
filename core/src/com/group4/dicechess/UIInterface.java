package com.group4.dicechess;

import java.util.ArrayList;
import java.util.Random;

import com.group4.dicechess.Representation.Board;
import com.group4.dicechess.Representation.Piece;
import com.group4.dicechess.Representation.Square;

public class UIInterface {
    private Board board;
    public int turnCounter;
    private int whiteScore;
    private int blackScore;
    private int diceRoll;
    private ArrayList<ArrayList<Square>> moveList;
    private ArrayList<Piece> legalPieces;


    public UIInterface(){
        board = new Board();
        turnCounter = -1;
        whiteScore = 0;
        blackScore = 0;
        moveList = new ArrayList<ArrayList<Square>>();
        legalPieces = new ArrayList<Piece>();
    }

    public Board getBoard(){
        return board;
    }

    public boolean legalMovesAreAvailable(int diceRoll){
        this.diceRoll = diceRoll;
        boolean white = turnCounter % 2 == 0 ? true : false;
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

    public boolean isLegalPiece(int row, int col){
        Piece piece = board.getSquare(row, col).getPiece();
        if(piece == null || piece.getWhiteStatus() != (turnCounter % 2 == 0) || (piece.getDiceChessId() != diceRoll && !piece.getId().equals("P"))){
            return false;
        }
        return legalPieces.contains(piece);
    }

    public boolean isLegalPieceChess(int row, int col){
        Piece piece = board.getSquare(row, col).getPiece();
        if(piece == null || piece.getWhiteStatus() != (turnCounter % 2 == 0)){
            return false;
        }
        return legalPieces.contains(piece);
    }

    public boolean isLegalMove(int startRow, int startCol, int row, int col){
        return moveList.get(legalPieces.indexOf(board.getSquare(startRow, startCol).getPiece())).contains(board.getSquare(row, col));
    }

    public ArrayList<Square> getLegalMoves(int row, int col){
        return moveList.get(legalPieces.indexOf(board.getSquare(row, col).getPiece()));
    }

    public void movePiece(int startRow, int startCol, int row, int col){
        boolean white = turnCounter % 2 == 0 ? true : false;
        if(white){
            whiteScore = board.movePiece(board.getSquare(startRow, startCol), board.getSquare(row, col), this.diceRoll);
        }
        else{
            blackScore = board.movePiece(board.getSquare(startRow, startCol), board.getSquare(row, col), this.diceRoll);
        }
    }

    public void prepareTurn(){
        legalPieces.clear();
        moveList.clear();
        ArrayList<Piece> allPieces = turnCounter % 2 == 0 ? board.getWhitePieces() : board.getBlackPieces();
        for(Piece piece : allPieces){

            ArrayList<Square> moves = piece.getPossibleMoves(board, board.getSquare(piece.getRow(), piece.getCol()));
            if(moves.size() > 0){
                legalPieces.add(piece);
                moveList.add(moves);
            }
        }
    }

    public int diceRoll(){
        ArrayList<Integer> rolls = new ArrayList<Integer>();
        legalPieces.clear();
        moveList.clear();
        ArrayList<Piece> allPieces = turnCounter % 2 == 0 ? board.getWhitePieces() : board.getBlackPieces();
        for(Piece piece : allPieces){
            ArrayList<Square> moves = piece.getPossibleMoves(board, board.getSquare(piece.getRow(), piece.getCol()));
            if(moves.size() > 0){
                legalPieces.add(piece);
                moveList.add(moves);
                if(!rolls.contains(piece.getDiceChessId())){
                    rolls.add(piece.getDiceChessId());
                }
            }
        }
        Random rand = new Random();
        this.diceRoll = rolls.get(rand.nextInt(rolls.size()));
        return this.diceRoll;
    }

    public boolean gameOver(){
        ArrayList<Piece> white = board.getWhiteCaptured();
        ArrayList<Piece> black = board.getBlackCaptured();
        for(int i = 0; i < white.size(); i++){
            if(white.get(i).getId().equals("K")){
                return true;
            }
        }
        for(int i = 0; i < black.size(); i++){
            if(black.get(i).getId().equals("K")){
                return true;
            }
        }
        return false;
    }

    public int getWhiteScore(){
        return whiteScore;
    }

    public int getBlackScore(){
        return blackScore;
    }
    
    public int getTurnCount(){
        return turnCounter;
    }
}
