package com.group4.dicechess;

import java.util.ArrayList;
import java.util.Random;

import com.group4.dicechess.Representation.Board;
import com.group4.dicechess.Representation.Move;
import com.group4.dicechess.Representation.Piece;
import com.group4.dicechess.agents.rl_agent.Training;

import static com.group4.dicechess.agents.rl_agent.utils.Utils.isZeroMatrix;

public class GameState {

    private Board board;
    public int turnCounter;
    private int whiteScore;
    private int blackScore;
    private int diceRoll;
    private ArrayList<ArrayList<Move>> moveList;
    private ArrayList<Piece> legalPieces;
    private static boolean isOver;
    private boolean isWhitesTurn;

    public GameState(){
        board = new Board();
        turnCounter = -1;
        whiteScore = 0;
        blackScore = 0;
        moveList = new ArrayList<ArrayList<Move>>();
        legalPieces = new ArrayList<Piece>();
        diceRoll = 1;
        isOver = false;
        isWhitesTurn = true;
    }

    public void reset(){
        board = new Board();
        turnCounter = -1;
        whiteScore = 0;
        blackScore = 0;
        moveList = new ArrayList<>();
        legalPieces = new ArrayList<>();
        diceRoll = 1;
        isOver = false;
        isWhitesTurn = true;
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
            if(m.destination().getRow() == row && m.destination().getCol() == col){
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

    public void movePiece(Move move){

        Training.totalMoves++;

        if(isWhitesTurn){
            whiteScore = board.movePiece(move);
            isWhitesTurn = false;
        }
        else {
            blackScore = board.movePiece(move);
            isWhitesTurn = true;
        }
        turnCounter++;
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

    public ArrayList<ArrayList<Move>> getPieceMoves(boolean white, int piece){
        ArrayList<Piece> pieces;

        if (white)
            pieces = (ArrayList<Piece>) board.getWhitePieces().clone();
        else
            pieces = (ArrayList<Piece>) board.getBlackPieces().clone();

        pieces.removeIf(n -> (n.getDiceChessId() != piece));
        return getMovesPieces(pieces);
    }

    public ArrayList<ArrayList<Move>> getTeamMoves(boolean white){
        ArrayList<Piece> pieces;

        if (white)
            pieces = (ArrayList<Piece>) board.getWhitePieces().clone();
        else
            pieces = (ArrayList<Piece>) board.getBlackPieces().clone();

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
        ArrayList<Integer> rolls = new ArrayList<>();
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

    public boolean isGameOver(){
        return isOver;
    }

    public static void kingCaptured(){
        isOver = true;
    }

    public boolean isWhitesTurn(){ return this.isWhitesTurn;}

    public int getWhiteScore(){return whiteScore;}
    public int getBlackScore(){return blackScore;}
    public int getTurnCount(){return turnCounter;}
    public int getDiceRoll(){return diceRoll;}
}