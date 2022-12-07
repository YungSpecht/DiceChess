package com.group4.dicechess.agents.basic_agents;

import java.util.ArrayList;
import java.util.Random;

import com.group4.dicechess.GameState;
import com.group4.dicechess.Representation.Move;
import com.group4.dicechess.agents.Bot;

public class GreedyBot implements Bot {
    private GameState state;
    public int diceRollResult;

    public GreedyBot(GameState state){
        this.state = state;
        this.diceRollResult = 0;
    }

    @Override
    public Move getMove() {
        state.diceRoll();
        this.diceRollResult = state.getDiceRoll();
        ArrayList<Move> possibleMoves = state.getPossibleMoves();
        ArrayList<Move> bestMoves = new ArrayList<Move>();
        int value = 0;
        for(Move m : possibleMoves){
            if(m.getCaptureValue() > value){
                value = m.getCaptureValue();
                bestMoves.clear();
                bestMoves.add(m);
            }
            else if(m.getCaptureValue() == value){
                bestMoves.add(m);
            }
        }
        Random rand = new Random();
        return bestMoves.get(rand.nextInt(bestMoves.size()));
    }

    @Override
    public int getRoll() {
        return this.diceRollResult;
    }
}
