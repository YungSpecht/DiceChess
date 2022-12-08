package com.group4.dicechess;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Game;
import com.group4.dicechess.Representation.Board;
import com.group4.dicechess.Representation.Move;
import com.group4.dicechess.Representation.Piece;

public class GameState {

    public Board board;
    public int turnCounter;
    private int whiteScore;
    private int blackScore;
    private int diceRoll;
    private ArrayList<Integer> rolls;
    private ArrayList<Move> moveHistory;
    private ArrayList<ArrayList<Move>> moveList;
    private ArrayList<Piece> legalPieces;

    public GameState(){
        board = new Board();
        turnCounter = 0;
        whiteScore = 0;
        blackScore = 0;
        rolls = new ArrayList<Integer>();
        moveHistory = new ArrayList<Move>();
        moveList = new ArrayList<ArrayList<Move>>();
        legalPieces = new ArrayList<Piece>();
        prepareNextTurn();
    }

    public boolean isLegalPiece(int row, int col){
        Piece piece = board.getSquare(row, col).getPiece();
        if(piece == null || piece.getWhiteStatus() != (turnCounter % 2 == 0) ||  (piece.getDiceChessId() != diceRoll && !isPromotingPawn(piece))){
            return false;
        }
        return legalPieces.contains(piece);
    }

    public boolean isPromotingPawn(Piece piece){
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

    public ArrayList<Move> getPossibleMoves(){
        ArrayList<Move> result = new ArrayList<Move>();
        for(ArrayList<Move> list : moveList){
            for(Move m : list){
                if(m.getPiece().getDiceChessId() == diceRoll){
                    result.add(m);
                }
            }
        }
        return result;
    }

    public int boardEvaluationFunc(){
        int blackEval = 0;

        for(int i = 0; i < board.getBlackPieces().size(); i++) {
            blackEval += board.getBlackPieces().get(i).getValue();
        }
        return blackEval;
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

    public void movePiece(int startRow, int startCol, int row, int col, boolean botMove){
        //TODO make sure this move is not null
        Move move = getMove(startRow, startCol, row, col);
        boolean white = turnCounter % 2 == 0 ? true : false;
        if(white){
            whiteScore = board.movePiece(move, this.diceRoll, botMove);
        }
        else{
            blackScore = board.movePiece(move, this.diceRoll, botMove);
        }
        moveHistory.add(move);
        turnCounter++;
        prepareNextTurn();
    }

    public void reverseLastMove(){
        Move lastMove = moveHistory.get(moveHistory.size()-1);
        Piece previousMovedPiece = moveHistory.size() < 3 ? null : moveHistory.get(moveHistory.size()-3).getPiece();
        board.reverseMove(lastMove, previousMovedPiece);
        turnCounter--;
        double capturedVal = moveHistory.get(moveHistory.size()-1).getCaptureValue();
        if(moveHistory.get(moveHistory.size()-1).getPiece().getWhiteStatus()){
            whiteScore -= capturedVal;
        }
        else{
            blackScore -= capturedVal;
        }
        moveHistory.remove(moveHistory.size()-1);
        prepareNextTurn();
        diceRoll = lastMove.getPiece().getDiceChessId();
    }


    public int diceRoll(){
        if(diceRoll == 0){
            Random rand = new Random();
            this.diceRoll = rolls.get(rand.nextInt(rolls.size()));
        }
        return this.diceRoll;
    }

    public static void main(String[] args) {
        GameState test = new GameState();
        test.testPromo();
    }

    public void testCastling(){
        GameState test = new GameState();
        test.movePiece(6, 6, 4,6, true);
        test.movePiece(1, 1, 3,1, true);
        test.movePiece(7, 6, 5,7, true);
        test.movePiece(1, 4, 3,4, true);
        test.movePiece(7, 5, 6,6, true);
        test.movePiece(1, 2, 3,2, true);
        test.getBoard().printBoard();
        test.movePiece(7, 4, 7,6, true);     // castling
        test.getBoard().printBoard();
        test.reverseLastMove();                                                 // reverse
        test.getBoard().printBoard();
    }

    public void testEnPassant(){
        GameState test = new GameState();
        test.movePiece(6, 0, 4,0, true);
        test.movePiece(1, 1, 2,1, true);
        test.movePiece(4, 0, 3,0, true);
        test.movePiece(2, 1, 3,1, true);
        test.movePiece(6, 5, 4,5, true);
        test.getBoard().printBoard();
        test.movePiece(3, 1, 4,1, true);
        test.getBoard().printBoard();
        test.movePiece(6, 2, 4,2, true);
        test.getBoard().printBoard();
    }

    public void testPromo(){
        GameState test = new GameState();
        test.movePiece(6, 0, 4,0, true);
        test.movePiece(1, 1, 3,1, true);
        test.movePiece(6, 1, 4,1, true);
        test.movePiece(1, 4, 3,4, true);
        test.movePiece(4, 0, 3,1, true);
        test.movePiece(3, 4, 4,4, true);
        test.movePiece(3, 1, 2,1, true);
        test.movePiece(1, 5, 3,5, true);
        test.movePiece(2, 1, 1,1, true);
        test.movePiece(3, 5, 4,5, true);
        test.getBoard().printBoard();
        test.setDiceRoll(1);
        test.movePiece(1, 1, 0,0, true);     // promotion
        //test.movePiece(4, 5, 5,5, true);
        test.getBoard().printBoard();
    }

    private void prepareNextTurn(){
        rolls.clear();
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
        diceRoll = 0;
    }

    public boolean gameOver(){
        ArrayList<Piece> white = board.getWhiteCaptured();
        ArrayList<Piece> black = board.getBlackCaptured();
        for(int i = 0; i < white.size(); i++){
            if(white.get(i).getId().equals("K")){
                System.out.println("------GAME OVER------");
                return true;
            }
        }
        for(int i = 0; i < black.size(); i++){
            if(black.get(i).getId().equals("K")){
                System.out.println("------GAME OVER------");
                return true;
            }
        }
        return false;
    }

    private Move getMove(int startRow, int startCol, int endRow, int endCol){
        for(ArrayList<Move> l : moveList){
            for(Move m : l){
                if(m.getStart().getRow() ==  startRow && m.getStart().getCol() == startCol && m.getDestination().getRow() == endRow && m.getDestination().getCol() == endCol){
                    return m;
                }
            }
        }
        return null;
    }
 

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public GameState copy(){
        GameState copy = new GameState();
        copy.setBoard(this.getBoard().copy());
        copy.setTurnCounter(turnCounter);
        copy.setWhiteScore(whiteScore);
        copy.setBlackScore(blackScore);
        copy.setDiceRoll(diceRoll);
        ArrayList<Integer> r = new ArrayList<Integer>();
        for(int i : rolls){
            r.add(i);
        }
        copy.setRollsList(r);
        ArrayList<Move> mH = new ArrayList<Move>();
        for(Move m : moveHistory){
            mH.add(m.copy());
        }
        copy.setMoveHistory(mH);
        ArrayList<ArrayList<Move>> mL = new ArrayList<ArrayList<Move>>();
        for(ArrayList<Move> list : moveList){
            ArrayList<Move> ml = new ArrayList<Move>();
            for(Move m : list){
                ml.add(m.copy());
            }
            mL.add(ml);
        }
        copy.setMoveList(mL);
        ArrayList<Piece> lP = new ArrayList<Piece>();
        for(Piece p : legalPieces){
            lP.add(p.copy());
        }
        copy.setLegalPieces(lP);
        return copy;
    }

    public Board getBoard(){
        return board;
    }

    public void setBoard(Board board){
        this.board = board;
    }

    public int getTurnCounter(){
        return turnCounter;
    }

    public void setTurnCounter(int turnCounter){
        this.turnCounter = turnCounter;
    }

    public int getWhiteScore(){return whiteScore;}
    public void setWhiteScore(int whiteScore){this.whiteScore = whiteScore;}
    public int getBlackScore(){return blackScore;}
    public void setBlackScore(int blackScore){this.blackScore = blackScore;}
    public int getDiceRoll(){return diceRoll;}
    public void setDiceRoll(int diceRoll){
        this.diceRoll = diceRoll;
    }

    public ArrayList<Integer> getRollsList(){
        return rolls;
    }

    public void setRollsList(ArrayList<Integer> rolls){
        this.rolls = rolls;
    }

    public ArrayList<Move> getMoveHistory(){
        return moveHistory;
    }

    public void setMoveHistory(ArrayList<Move> moveHistory){
        this.moveHistory = moveHistory;
    }

    public ArrayList<ArrayList<Move>> getMoveList(){
        return moveList;
    }

    public void setMoveList(ArrayList<ArrayList<Move>> moveList){
        this.moveList = moveList;
    }

    public ArrayList<Piece> getLegalPieces(){
        return legalPieces;
    }

    public void setLegalPieces(ArrayList<Piece> legalPieces){
        this.legalPieces = legalPieces;
    }
}