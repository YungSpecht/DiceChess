package com.group4.dicechess.agents.MCTS;

import com.group4.dicechess.GameState;
import com.group4.dicechess.Representation.Move;
import com.group4.dicechess.agents.Bot;
import com.group4.dicechess.agents.basic_agents.Node;

public class MonteCarloTreeSearch implements Bot {

    public GameState state;
    public double tunable_c = Math.sqrt(2);
    public int diceRollResult;
    public int maxIterations = 100;
    public int currentIteration;
    public double currentBestChild;
    public double temp;
    public double result;


    public MonteCarloTreeSearch(GameState state){
        this.state = state;
        this.diceRollResult = 0;
    }

    public void createMCTSTree(){
        NodeMCTS root = new NodeMCTS(null, null);
        while(maxIterations >= currentIteration){
            NodeMCTS currentNode = root;
            GameState currentState = state;
            while (currentNode.children.isEmpty()){
                for(NodeMCTS child : currentNode.children){
                    temp = uct_formula(child.getMean_value(), child.getVisited(), child.parent.getVisited());
                    if(temp > currentBestChild){
                        currentBestChild = temp;
                        currentNode = child;
                    }
                }
            }
            // expansion
            if(currentNode.getVisited() > 0){
            }
            while (true){ // simulation
                break;
            }
            while(currentNode.hasParent()){
                currentNode.update(result);
                currentNode = currentNode.parent;
            }
            currentIteration++;
        }
    }

    // UCB - Upper confidence bounds formula - Exploration / Exploitation
    public double uct_formula(double mean_node_val, double small_n, double big_n){
        return mean_node_val + tunable_c*(Math.sqrt((Math.log(big_n))/(small_n)));
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

    @Override
    public int getRoll() {
        return this.diceRollResult;
    }
}