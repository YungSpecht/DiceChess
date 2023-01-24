package com.group4.dicechess.agents.Expectimax;

import com.group4.dicechess.GameState;
import com.group4.dicechess.Representation.Move;
import com.group4.dicechess.agents.Bot;
import com.group4.dicechess.agents.Expectimax.ExpectimaxTree;

public class ExpectimaxBot implements Bot{

    private GameState state;
    private boolean nnEval = true;
    public int diceRollResult;

    public ExpectimaxBot(GameState state){
        this.state = state;
        this.diceRollResult = 0;
    }

    public ExpectimaxBot(GameState state, boolean nnEval){
        this.state = state;
        this.diceRollResult = 0;
        this.nnEval = nnEval;
    }
    @Override
    public Move getMove() {
        diceRollResult =  state.diceRoll();
        ExpectimaxTree tree = new ExpectimaxTree(state, nnEval);
        tree.buildTree(3);
        return tree.getBestMove();
    }

    @Override
    public int getRoll() {
        return this.diceRollResult;
    }
    
}
