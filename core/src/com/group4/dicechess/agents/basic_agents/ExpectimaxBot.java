package com.group4.dicechess.agents.basic_agents;

import com.group4.dicechess.GameState;
import com.group4.dicechess.Representation.Move;
import com.group4.dicechess.agents.Bot;

public class ExpectimaxBot implements Bot{

    private GameState state;
    public int diceRollResult;

    public ExpectimaxBot(GameState state){
        this.state = state;
        this.diceRollResult = 0;
    }

    @Override
    public Move getMove() {
        diceRollResult =  state.diceRoll();
        ExpectimaxTree tree = new ExpectimaxTree(state);
        tree.buildTree(3);
        return tree.getBestMove();
    }

    @Override
    public int getRoll() {
        return this.diceRollResult;
    }
    
}
