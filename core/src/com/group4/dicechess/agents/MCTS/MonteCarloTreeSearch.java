package com.group4.dicechess.agents.MCTS;

import com.group4.dicechess.GameState;
import com.group4.dicechess.Representation.Move;
import com.group4.dicechess.agents.Bot;
import com.group4.dicechess.agents.basic_agents.Node;
import com.group4.dicechess.agents.basic_agents.RandomBot;

import java.util.ArrayList;

public class MonteCarloTreeSearch implements Bot {

    public MonteCarloTreeSearch(GameState state){
        this.state = state;
        this.diceRollResult = 0;
    }

    public static void main(String[] args) {
    }

    public Move createMCTSTree() throws CloneNotSupportedException {
        System.out.println("initial");
        this.state.diceRoll();
        NodeMCTS root = new NodeMCTS(null, null, this.state);
        GameState currentState = state;

        while(maxIterations >= currentIteration){
            System.out.println("r");
            currentNode = root;
            reset();
            boolean flag = false;
            while (!currentNode.children.isEmpty()){                      // Selection
                for(NodeMCTS child : currentNode.children){
                    temp = uct_formula(child.getMean_value(), child.getVisited(), child.parent.getVisited());
                    System.out.println(temp);
                    if(temp > currentBestChild || !flag){
                        currentBestChild = temp;
                        currentNode.getState().movePiece(child.getMove().getStart().getRow(), child.getMove().getStart().getCol(),child.getMove().getDestination().getRow() , child.getMove().getDestination().getCol());
                        currentNode.getState().getBoard().printBoard();
                        currentNode.getState().setDiceRoll(0);
                        child.state = currentNode.state;
                        System.out.println(child.getMove());
                        currentNode = child;
                        flag = true;
                    }
                }
                System.out.println("Im here");
            }
            System.out.println("gets here");

            if(currentNode.getVisited() != 0 || currentNode == root){
                ArrayList<Move> possibleMoves = currentNode.getState().getPossibleMoves();
                ArrayList<Move> pM = new ArrayList<Move>();
                pM.addAll(possibleMoves);

                for(Move m : pM){
                    currentNode.getState().addMoves(pM);
                    currentNode.getState().movePiece(m.getStart().getRow(), m.getStart().getCol(), m.getDestination().getRow(), m.getDestination().getCol());
                    currentNode.getState().setDiceRoll(0);
                    currentNode.getState().getBoard().printBoard();
                    childNode = new NodeMCTS(currentNode, m, nextState);
                    currentNode.children.add(childNode);
                    currentNode.getState().getBoard().printBoard();
                    currentNode.getState().reverseLastMove();
                    currentNode.getState().getBoard().printBoard();
                }
                System.out.println(currentNode.children.size());
            } else {
                System.out.println("a");
                currentNode.getState().getBoard().printBoard();
                ArrayList<Move> simulatedMoves = new ArrayList<>();
                while (currentDepth <= depth){                           // Simulation
                    System.out.println("d");
                    randBot = new RandomBot(currentNode.getState());
                    simulatedMove = randBot.getMove();
                    currentNode.getState().getBoard().printBoard();
                    System.out.println("v");
                    currentNode.getState().movePiece(simulatedMove.getStart().getRow(), simulatedMove.getStart().getCol(), simulatedMove.getDestination().getRow(), simulatedMove.getDestination().getCol());
                    simulatedMoves.add(simulatedMove);
                    currentDepth++;
                }
                result = currentNode.getState().boardEvaluationFunc();
                currentNode.getState().reverseLastMoves(simulatedMoves);

                while(currentNode.hasParent()){                         // Backpropagation
                    currentNode.update(result);
                    System.out.println("backpropagation");
                    currentNode = currentNode.parent;
                    // update state
                }
                currentIteration++;
            }
        }
        currentBestChild = 0.0;
        NodeMCTS tempNode;
        for(NodeMCTS child : currentNode.children){
            if(child.getMean_value() > currentBestChild){
                currentBestChild = child.getMean_value();
                tempNode = child;
            }
        }
        System.out.println(root.children.size());
        System.out.println("finds it");
        return currentNode.getMove();
    }

    // UCB - Upper confidence bounds formula - Exploration / Exploitation
    public double uct_formula(double mean_node_val, double small_n, double big_n){
        return mean_node_val + tunable_c*(Math.sqrt((Math.log(big_n))/(small_n)));
    }

    public void reset(){
        this.result = 0.0;
    }

    @Override
    public Move getMove() throws CloneNotSupportedException {
        return this.createMCTSTree();
    }

    @Override
    public int getRoll() {
        return this.diceRollResult;
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

    public RandomBot randBot;
    public GameState state;
    public double tunable_c = Math.sqrt(2);
    public int diceRollResult;
    public int maxIterations = 10;
    public int depth = 5;
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