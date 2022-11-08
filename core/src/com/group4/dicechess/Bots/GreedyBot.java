package com.group4.dicechess.Bots;

import java.util.ArrayList;
import java.util.Random;

import com.group4.dicechess.GameState;
import com.group4.dicechess.Representation.Move;
import com.group4.dicechess.Representation.Piece;

public class GreedyBot implements Bot {
    private GameState state;

    public GreedyBot(GameState state){
        this.state = state;
    }

    @Override
    public Move getMove() {
        ArrayList<ArrayList<Move>> allMoves = state.getMoveList();
        ArrayList<Move> allPossibleMoves = new ArrayList<Move>();
        ArrayList<Move> allPossibleCapturingMoves = new ArrayList<Move>();
        for(ArrayList<Move> list : allMoves){
            for(Move m : list){
                if(m.getPiece().getDiceChessId() == state.getDiceRoll()){
                    allPossibleMoves.add(m);
                    if(m.getDestination().getPiece() != null){
                        allPossibleCapturingMoves.add(m);
                    }
                }
            }
        }
        Random rand = new Random();
        if(allPossibleCapturingMoves.size() > 0){
            Move bestMove = allPossibleCapturingMoves.get(rand.nextInt(allPossibleCapturingMoves.size()));
            for(Move m : allPossibleCapturingMoves){
                if(m.getDestination().getPiece().getValue() > bestMove.getDestination().getPiece().getValue()){
                    bestMove = m;
                }
            }
            return bestMove;
        }
        return allPossibleMoves.get(rand.nextInt(allPossibleMoves.size()));
    }
}
