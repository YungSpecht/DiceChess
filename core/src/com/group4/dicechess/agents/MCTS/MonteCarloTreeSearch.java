package com.group4.dicechess.agents.MCTS;

import com.group4.dicechess.GameState;
import com.group4.dicechess.Representation.Move;
import com.group4.dicechess.agents.Bot;
import com.group4.dicechess.agents.basic_agents.Node;
import com.group4.dicechess.agents.basic_agents.RandomBot;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MonteCarloTreeSearch implements Bot {

    public MonteCarloTreeSearch(GameState state){
        this.state = state;
        this.diceRollResult = 0;
    }

    public static void main(String[] args) {
    }

    public Move createMCTSTree(){
        System.out.println("Begin MCTS");
        this.state.diceRoll();
        NodeMCTS root = new NodeMCTS(null, null, this.state);
        GameState currentState = state;

        while(maxIterations >= currentIteration) {
            System.out.println("Start of iteration: " + currentIteration);
            currentNode = root;
            boolean flag = false;
            ArrayList<Move> simulatedMoves = new ArrayList<>();

            System.out.println("Selection");
            while (!currentNode.children.isEmpty()) {
                System.out.println("Selection depth +1");
                List<NodeMCTS> tempStor = currentNode.children;
                for (NodeMCTS child : tempStor) {
                    temp = uct_formula(child.getMean_value(), child.getVisited(), child.parent.getVisited());
                    if (temp > currentBestChild || !flag) {
                        currentBestChild = temp;
                        currentNode = child;
                        flag = true;
                    }
                }
                flag = false;
            }

            if(currentNode.getVisited() != 0 || currentNode == root){               // Expansion
                System.out.println("Expansion");
                if(currentNode != root){
                    System.out.println("a");
                    currentState.diceRoll();
                    System.out.println(currentNode.getMove().getStart().getRow());
                    currentNode.state.movePiece(currentNode.getMove().getStart().getRow(),currentNode.getMove().getStart().getCol(),currentNode.getMove().getDestination().getRow() ,currentNode.getMove().getDestination().getCol(), true);
                }
                ArrayList<Move> possibleMoves = currentNode.getState().getPossibleMoves();
                System.out.println(currentNode.getState().getPossibleMoves().size());
                ArrayList<Move> pM = new ArrayList<Move>();
                pM.addAll(possibleMoves);
                if(currentNode != root){
                    currentNode.getState().reverseLastMove();
                }
                for(Move m : pM){
                    childNode = new NodeMCTS(currentNode, m, currentNode.state);
                    currentNode.children.add(childNode);
                }
            } else {
                Random rand = new Random();
                System.out.println("Simulation");
                while (currentDepth <= depth){                           // Simulation
                    currentNode.getState().diceRoll();
                    simulatedMove = currentNode.getState().getPossibleMoves().get(rand.nextInt(currentNode.getState().getPossibleMoves().size()));
                    simulatedMoves.add(simulatedMove);
                    currentNode.getState().movePiece(simulatedMove.getStart().getRow(), simulatedMove.getStart().getCol(), simulatedMove.getDestination().getRow(), simulatedMove.getDestination().getCol(), true);
                    currentDepth++;
                }
                currentNode.state.reverseLastMoves(simulatedMoves);
                result = currentNode.getState().boardEvaluationFunc();
                // reverse all moves but keep them somehow.

                while(currentNode.hasParent()){                         // Backpropagation
                    currentNode.update(result);
                    System.out.println("backpropagation");
                    currentNode = currentNode.parent;
                    // update state
                }
            }

            currentIteration++;
        }
        currentNode = root;
        currentNode = currentNode.children.get(0);
        System.out.println("finds it");
        return currentNode.getMove();
    }

    // UCB - Upper confidence bounds formula - Exploration / Exploitation
    public double uct_formula(double mean_node_val, double small_n, double big_n){
        return mean_node_val + tunable_c*(Math.sqrt((Math.log(big_n))/(small_n)));
    }

    @Override
    public Move getMove() {
        return createMCTSTree();
    }

    @Override
    public int getRoll() {
        return this.diceRollResult;
    }

    public RandomBot randBot;
    public GameState state;
    public double tunable_c = Math.sqrt(2);
    public int diceRollResult;
    public int maxIterations = 33;
    public int depth = 40;
    public int currentDepth;
    public int currentIteration;
    public double currentBestChild;
    public NodeMCTS simulatedNode;
    public Move simulatedMove;
    public double temp;
    public double result;
    public NodeMCTS currentNode;
    public GameState nextState;
    public NodeMCTS childNode;
}