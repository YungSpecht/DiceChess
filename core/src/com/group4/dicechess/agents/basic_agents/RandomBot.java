package com.group4.dicechess.agents.basic_agents;

import java.util.ArrayList;
import java.util.Random;

import com.group4.dicechess.GameState;
import com.group4.dicechess.Representation.Move;
import com.group4.dicechess.agents.Bot;

public class RandomBot implements Bot {

    private GameState state;
    public int diceRollResult;

    public RandomBot(GameState state){
        this.state = state;
        this.diceRollResult = 0;
    }

    @Override
    public int getRoll(){
        return this.diceRollResult;
    }

    @Override
    public Move getMove() {
        state.diceRoll();
        this.diceRollResult = state.getDiceRoll();
        ArrayList<Move> possibleMoves = state.getPossibleMoves();
        for(Move m : possibleMoves){
            if(m.getCapturedPiece() != null && m.getCapturedPiece().getId().equals("K")){
                return m;
            }
        }
        Random rand = new Random();
        return possibleMoves.get(rand.nextInt(possibleMoves.size()));
    }
    
}
