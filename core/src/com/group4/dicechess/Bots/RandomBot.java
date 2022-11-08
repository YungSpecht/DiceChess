package com.group4.dicechess.Bots;

import java.util.ArrayList;
import java.util.Random;

import com.group4.dicechess.GameState;
import com.group4.dicechess.Representation.Move;

public class RandomBot implements Bot {

    private GameState state;

    public RandomBot(GameState state){
        this.state = state;
    }

    @Override
    public Move getMove() {
        ArrayList<ArrayList<Move>> allMoves = state.getMoveList();
        ArrayList<Move> possibleMoves = new ArrayList<Move>();
        for(ArrayList<Move> list : allMoves){
            for(Move m : list){
                if(m.getPiece().getDiceChessId() == state.getDiceRoll()){
                    possibleMoves.add(m);
                }
            }
        }
        Random rand = new Random();
        return possibleMoves.get(rand.nextInt(possibleMoves.size()));
    }
    
}
