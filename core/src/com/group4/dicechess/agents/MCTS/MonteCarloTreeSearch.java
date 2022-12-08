package com.group4.dicechess.agents.MCTS;

import com.group4.dicechess.GameState;
import com.group4.dicechess.Representation.Move;
import com.group4.dicechess.agents.Bot;
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
        this.state.diceRoll();
        NodeMCTS root = new NodeMCTS(null, null, this.state);


        while(maxIterations >= currentIteration) {
            System.out.println("Start of iteration: " + currentIteration);
            currentNode = root;
            currentDepth = 0;
            flag = false;
            ArrayList<Move> simulatedMoves = new ArrayList<>();
            ArrayList<Move> simulatedMovesSelection = new ArrayList<>();
            ArrayList<Integer> simulatedMovesSelectionDiceRolls = new ArrayList<>();

            //System.out.println("Selection");                                    // Selection
            while (!currentNode.children.isEmpty()) {
                //System.out.println("Selection - depth + 1");
                tempStorage = currentNode.children;
                for (NodeMCTS child : tempStorage) {
                    temp = uct_formula(child.getMean_value(), child.getVisited(), child.parent.getVisited());
                    if (temp > currentBestChild || !flag) {
                        currentBestChild = temp;
                        currentNode = child;
                        flag = true;
                    }
                }
                simulatedMovesSelection.add(currentNode.getMove());
                simulatedMovesSelectionDiceRolls.add(currentNode.diceRoll);
                flag = false;
            }

            if(currentNode.getVisited() != 0 || currentNode == root){               // Expansion
                //System.out.println("Expansion");
                if(currentNode != root){
                    for (int i = 0; i < simulatedMovesSelection.size(); i++) {
                        currentNode.state.setDiceRoll(simulatedMovesSelectionDiceRolls.get(i));
                        currentNode.state.movePiece(simulatedMovesSelection.get(i).getStart().getRow(),simulatedMovesSelection.get(i).getStart().getCol(),simulatedMovesSelection.get(i).getDestination().getRow() ,simulatedMovesSelection.get(i).getDestination().getCol(), true);
                    }
                    currentNode.state.diceRoll();
                }
                ArrayList<Move> possibleMoves = currentNode.getState().getPossibleMoves();
                ArrayList<Move> pM = new ArrayList<>(possibleMoves);
                for(Move m : pM){
                    childNode = new NodeMCTS(currentNode, m, currentNode.state);
                    childNode.diceRoll = currentNode.state.getDiceRoll();
                    currentNode.children.add(childNode);
                }

                if(currentNode != root){
                    for (int i = 0; i < simulatedMovesSelection.size(); i++) {
                        currentNode.getState().reverseLastMove();
                    }
                }
            } else {
                Random rand = new Random();
                //System.out.println("Simulation");
                for (int i = 0; i < simulatedMovesSelection.size(); i++) {
                    currentNode.state.setDiceRoll(simulatedMovesSelectionDiceRolls.get(i));
                    currentNode.state.movePiece(simulatedMovesSelection.get(i).getStart().getRow(),simulatedMovesSelection.get(i).getStart().getCol(),simulatedMovesSelection.get(i).getDestination().getRow() ,simulatedMovesSelection.get(i).getDestination().getCol(), true);
                }
                while (currentDepth <= depth){                           // Simulation
                    currentNode.getState().diceRoll();
                    int a = currentNode.getState().getPossibleMoves().size();
                    simulatedMove = currentNode.getState().getPossibleMoves().get(rand.nextInt(a));
                    // check if move is game over. break and make result 10000000.
                    currentNode.getState().movePiece(simulatedMove.getStart().getRow(), simulatedMove.getStart().getCol(), simulatedMove.getDestination().getRow(), simulatedMove.getDestination().getCol(), true);
                    simulatedMoves.add(simulatedMove);
                    currentDepth++;
                }
                result = currentNode.getState().boardEvaluationFunc();

                for (int i = 0; i < simulatedMoves.size(); i++) {
                    currentNode.getState().reverseLastMove();
                }
                for (int i = 0; i < simulatedMovesSelection.size(); i++) {
                    currentNode.getState().reverseLastMove();
                }
                //System.out.println("backpropagation");
                while(currentNode.hasParent()){                         // Backpropagation
                    currentNode.update(result);
                    currentNode = currentNode.parent;
                }
                currentNode.update(result);
            }
            currentIteration++;
        }
        currentNode = root;
        int mostVisits = 0;
        int j = 0;
        System.out.println("Child node visits: ");
        for (int i = 0; i < root.children.size(); i++) {
            System.out.println(root.children.get(i).getVisited());
            if(root.children.get(i).getVisited() > mostVisits){
                mostVisits = root.children.get(i).getVisited();
                j = i;
            }
        }
        currentNode = currentNode.children.get(j);
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

    public GameState state;
    public Move simulatedMove;
    public NodeMCTS currentNode;
    public NodeMCTS childNode;
    public List<NodeMCTS> tempStorage;
    public double tunable_c = 2;
    public double currentBestChild;
    public double temp;
    public double result;
    public boolean flag;
    public int diceRollResult;
    public int maxIterations = 500;
    public int depth = 50;
    public int currentDepth;
    public int currentIteration;
}