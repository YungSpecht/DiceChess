package com.group4.dicechess.agents.MCTS;

import com.group4.dicechess.GameState;
import com.group4.dicechess.Representation.Move;
import com.group4.dicechess.agents.Bot;

public class MonteCarloTreeSearch implements Bot {

    private double tunable_c;
    private GameState state;

    public MonteCarloTreeSearch(GameState state){
        this.state = state;
    }

    /*  Algorithm - Monte-Carlo Tree Search

    1) Selection
        Starting at root node R, recursively select optimal child nodes (explained below) until a leaf node L is reached.

    2) Expansion
        If L is a not a terminal node (i.e. it does not end the game) then create one or more child nodes and select one C.

    3) Simulation
        Run a simulated playout from C until a result is achieved.

    4) Backpropagation
        Update the current move sequence with the simulation result.
        Each node must contain two important pieces of information: an estimated value based on simulation results
        and the number of times it has been visited.
        In its simplest and most memory efficient implementation, MCTS will add one child node per iteration.
        Note, however, that it may be beneficial to add more than one child node per iteration depending on the application
     */

    @Override
    public Move getMove() {
        return null;
    }

    // UCB - Upper confidence bounds formula - Exploration / Exploitation
    public double uct_formula(double mean_node_val, double small_n, double big_n){
        return mean_node_val + tunable_c*(Math.sqrt((Math.log(big_n))/(small_n)));
    }
}