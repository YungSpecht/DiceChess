package com.group4.dicechess;

import java.util.ArrayList;

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
        boolean white = turnCounter % 2 == 0 ? true : false;
        Piece piece = board.getSquare(row, col).getPiece();
        if(piece == null || piece.getWhiteStatus() != white || piece.getDiceChessId() != diceRoll){
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
            whiteScore = board.movePiece(board.getSquare(startRow, startCol), board.getSquare(row, col));
        }
        else{
            blackScore = board.movePiece(board.getSquare(startRow, startCol), board.getSquare(row, col));
        }
    }

    public boolean gameOver(){
        ArrayList<Piece> white = board.getWhiteCaptured();
        ArrayList<Piece> black = board.getBlackCaptured();
        for(int i = 0; i < white.size(); i++){
            if(white.get(i).getId().equals("K") || black.get(i).getId().equals("K")){
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
