package com.group4.dicechess;

import java.util.ArrayList;
import java.util.Random;
import java.util.function.Predicate;

import com.group4.dicechess.Representation.Board;
import com.group4.dicechess.Representation.Move;
import com.group4.dicechess.Representation.Piece;

public class GameState {

    private Board board;
    public int turnCounter;
    private int whiteScore;
    private int blackScore;
    private int diceRoll;
    private ArrayList<ArrayList<Move>> moveList;
    private ArrayList<Piece> legalPieces;

    public GameState(){
        board = new Board();
        turnCounter = -1;
        whiteScore = 0;
        blackScore = 0;
        moveList = new ArrayList<ArrayList<Move>>();
        legalPieces = new ArrayList<Piece>();
        diceRoll = 1;
    }

    public Board getBoard(){
        return board;
    }

    public ArrayList<ArrayList<Move>> getMoveList(){
        return moveList;
    }

    public boolean isLegalPiece(int row, int col){
        Piece piece = board.getSquare(row, col).getPiece();
        if(piece == null || piece.getWhiteStatus() != (turnCounter % 2 == 0) ||  (piece.getDiceChessId() != diceRoll && !isPromotingPawn(piece))){
            return false;
        }
        return legalPieces.contains(piece);
    }

    private boolean isPromotingPawn(Piece piece){
        if(diceRoll == 6){
            return false;
        }
        else if(piece.getWhiteStatus() && piece.getId().equals("P") && piece.getRow() == 1){
            return true;
        }
        else if(!piece.getWhiteStatus() && piece.getId().equals("P") && piece.getRow() == 6){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isLegalPieceChess(int row, int col){
        Piece piece = board.getSquare(row, col).getPiece();
        if(piece == null || piece.getWhiteStatus() != (turnCounter % 2 == 0)){
            return false;
        }
        return legalPieces.contains(piece);
    }

    public boolean isLegalMove(int startRow, int startCol, int row, int col){
        ArrayList<Move> moves = getLegalMoves(startRow, startCol);
        for(Move m : moves){
            if(m.getDestination().getRow() == row && m.getDestination().getCol() == col){
                return true;
            }
        }
        return false;
    }

    public ArrayList<Move> getLegalMoves(int row, int col){
        return moveList.get(legalPieces.indexOf(board.getSquare(row, col).getPiece()));
    }

    public void movePiece(int startRow, int startCol, int row, int col){
        boolean white = turnCounter % 2 == 0;
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
            ArrayList<Move> moves = piece.getPossibleMoves(board);
            if(moves.size() > 0){
                legalPieces.add(piece);
                moveList.add(moves);
            }
        }
    }

    public ArrayList<ArrayList<Move>> getPieceMoves(boolean white, final int piece){
        ArrayList<Piece> pieces = white? board.getWhitePieces() : board.getBlackPieces();
        pieces.removeIf(n -> (n.getDiceChessId() != piece));
        return getMovesPieces(pieces);
    }

    public ArrayList<ArrayList<Move>> getTeamMoves(boolean white){
        ArrayList<Piece> pieces = white? board.getWhitePieces() : board.getBlackPieces();
        return getMovesPieces(pieces);
    }

    private ArrayList<ArrayList<Move>> getMovesPieces(ArrayList<Piece> pieces){
        ArrayList<ArrayList<Move>> out = new ArrayList<>();
        for(Piece piece : pieces){
            ArrayList<Move> moves = piece.getPossibleMoves(board);
            if(moves.isEmpty()) continue;
            out.add(moves);
        }
        return out;
    }

    public int diceRoll(){
        ArrayList<Integer> rolls = new ArrayList<Integer>();
        legalPieces.clear();
        moveList.clear();
        ArrayList<Piece> allPieces = turnCounter % 2 == 0 ? board.getWhitePieces() : board.getBlackPieces();
        for(Piece piece : allPieces){
            ArrayList<Move> moves = piece.getPossibleMoves(board);
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

    public boolean isWhitesTurn(){ return turnCounter % 2 == 0;}

    public int getWhiteScore(){return whiteScore;}
    public int getBlackScore(){return blackScore;}
    public int getTurnCount(){return turnCounter;}
    public int getDiceRoll(){return diceRoll;}
}