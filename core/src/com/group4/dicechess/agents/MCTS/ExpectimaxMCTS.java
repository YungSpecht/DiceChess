package com.group4.dicechess.agents.basic_agents;

import com.group4.dicechess.GameState;
import com.group4.dicechess.Representation.Move;
import com.group4.dicechess.agents.Bot;

public class ExpectimaxMCTS implements Bot{

    private GameState state;
    public int diceRollResult;

    public ExpectimaxMCTS(GameState state){
        this.state = state;
        this.diceRollResult = 0;
    }

    @Override
    public Move getMove() {
        diceRollResult =  state.diceRoll();
        ExpectimaxTree tree = new ExpectimaxTree(state);
        tree.buildTree(2);
        return tree.getBestMove();
    }

    @Override
    public int getRoll() {
        return this.diceRollResult;
    }

}
