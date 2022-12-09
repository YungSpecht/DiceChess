package com.group4.dicechess;

import java.util.ArrayList;
import java.util.Random;

import com.group4.dicechess.Representation.Board;
import com.group4.dicechess.Representation.Move;
import com.group4.dicechess.Representation.Piece;
import com.group4.dicechess.Representation.Tables;
import com.group4.dicechess.agents.rl_agent.Training;
import com.group4.dicechess.agents.rl_agent.utils.RLMove;

import static com.group4.dicechess.agents.rl_agent.utils.Utils.isZeroMatrix;

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
    private boolean isWhitesTurn;

    public GameState(){
        board = new Board();
        turnCounter = 0;
        whiteScore = 0;
        blackScore = 0;
        rolls = new ArrayList<Integer>();
        moveHistory = new ArrayList<Move>();
        moveList = new ArrayList<ArrayList<Move>>();
        legalPieces = new ArrayList<Piece>();
        isWhitesTurn = true;
        prepareNextTurn();
    }

    public void reset(){
        board = new Board();
        turnCounter = 0;
        whiteScore = 0;
        blackScore = 0;
        moveList = new ArrayList<>();
        legalPieces = new ArrayList<>();
        isWhitesTurn = true;
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

    public void movePiece(RLMove move){

        Training.totalMoves++;

        Move boardMove = new Move(move.start(), move.destination(), move.piece());

        if(isWhitesTurn){
            whiteScore = board.movePiece(boardMove, move.piece().getDiceChessId(), true);
            isWhitesTurn = false;
        }
        else {
            blackScore = board.movePiece(boardMove, move.piece().getDiceChessId(), true);
            isWhitesTurn = true;
        }
        turnCounter++;
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

    public int boardEvaluationFunc(){
        int blackEval = 0;

        for(int i = 0; i < board.getBlackPieces().size(); i++) {
            blackEval -= board.getBlackPieces().get(i).getValue();
        }
        return blackEval;

    }

    public int diceRoll(){
        if(diceRoll == 0){
            Random rand = new Random();
            this.diceRoll = rolls.get(rand.nextInt(rolls.size()));
        }
        return this.diceRoll;
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

    public int evaluate(){
        int result = 0;
        for(Piece p : board.getWhitePieces()){
            switch(p.getIdentifier()){
                case "P" : result += Tables.pawnWhite[p.getRow()][p.getCol()]; break;
                case "N" : result += Tables.knightWhite[p.getRow()][p.getCol()]; break;
                case "B" : result += Tables.bishopWhite[p.getRow()][p.getCol()]; break;
                case "R" : result += Tables.rookWhite[p.getRow()][p.getCol()]; break;
                case "Q" : result += Tables.queenWhite[p.getRow()][p.getCol()]; break;
                case "K" : result += Tables.kingWhite[p.getRow()][p.getCol()]; break;
            }
        }
        for(Piece p : board.getBlackPieces()){
            switch(p.getIdentifier()){
                case "P" : result -= Tables.pawnBlack[p.getRow()][p.getCol()]; break;
                case "N" : result -= Tables.knightBlack[p.getRow()][p.getCol()]; break;
                case "B" : result -= Tables.bishopBlack[p.getRow()][p.getCol()]; break;
                case "R" : result -= Tables.rookBlack[p.getRow()][p.getCol()]; break;
                case "Q" : result -= Tables.queenBlack[p.getRow()][p.getCol()]; break;
                case "K" : result -= Tables.kingBlack[p.getRow()][p.getCol()]; break;
            }
        }
        result += 9000*(board.count("K", true)-board.count("K", false))+900*(board.count("Q", true)-board.count("Q", false))+500*(board.count("R", true)-board.count("R", false))+300*(board.count("B", true)-board.count("B", false))+300*(board.count("N", true)-board.count("N", false))+100*(board.count("P", true)-board.count("P", false));
        return result;
    }

    public int evaluateMCTS(){
        int result = 0;
        for(Piece p : board.getWhitePieces()){
            switch(p.getIdentifier()){
                case "P" : result -= Tables.pawnWhite[p.getRow()][p.getCol()]; break;
                case "N" : result -= Tables.knightWhite[p.getRow()][p.getCol()]; break;
                case "B" : result -= Tables.bishopWhite[p.getRow()][p.getCol()]; break;
                case "R" : result -= Tables.rookWhite[p.getRow()][p.getCol()]; break;
                case "Q" : result -= Tables.queenWhite[p.getRow()][p.getCol()]; break;
                case "K" : result -= Tables.kingWhite[p.getRow()][p.getCol()]; break;
            }
        }
        for(Piece p : board.getBlackPieces()){
            switch(p.getIdentifier()){
                case "P" : result += Tables.pawnBlack[p.getRow()][p.getCol()]; break;
                case "N" : result += Tables.knightBlack[p.getRow()][p.getCol()]; break;
                case "B" : result += Tables.bishopBlack[p.getRow()][p.getCol()]; break;
                case "R" : result += Tables.rookBlack[p.getRow()][p.getCol()]; break;
                case "Q" : result += Tables.queenBlack[p.getRow()][p.getCol()]; break;
                case "K" : result += Tables.kingBlack[p.getRow()][p.getCol()]; break;
            }
        }
        result += 9000*(board.count("K", false)-board.count("K", true))+900*(board.count("Q", false)-board.count("Q", true))+500*(board.count("R", false)-board.count("R", true))+300*(board.count("B", false)-board.count("B", true))+300*(board.count("N", false)-board.count("N", true))+100*(board.count("P", false)-board.count("P", true));
        return result;
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

    public boolean isWhitesTurn(){
        return isWhitesTurn;
    }
}