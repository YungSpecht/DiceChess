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
            ArrayList<Move> simulatedMovesSelection = new ArrayList<>();

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
                        System.out.println(temp);
                    }
                }
                simulatedMovesSelection.add(currentNode.getMove());
                flag = false;
            }


            if(currentNode.getVisited() != 0 || currentNode == root){               // Expansion
                System.out.println("Expansion");

                if(currentNode != root){    // get it up-to-date board wise
                    for (int i = 0; i < simulatedMovesSelection.size(); i++) {
                        currentNode.state.movePiece(simulatedMovesSelection.get(i).getStart().getRow(),simulatedMovesSelection.get(i).getStart().getCol(),simulatedMovesSelection.get(i).getDestination().getRow() ,simulatedMovesSelection.get(i).getDestination().getCol(), true);
                    }
                    currentState.getBoard().printBoard();
                    currentNode.state.diceRoll();
                }
                ArrayList<Move> possibleMoves = currentNode.getState().getPossibleMoves();
                System.out.println(currentNode.getState().getPossibleMoves().size());
                ArrayList<Move> pM = new ArrayList<Move>();
                pM.addAll(possibleMoves);
                for(Move m : pM){
                    childNode = new NodeMCTS(currentNode, m, currentNode.state);
                    currentNode.children.add(childNode);
                }

                if(currentNode != root){    // re get it up to root
                    for (int i = 0; i < simulatedMovesSelection.size(); i++) {
                        currentNode.getState().reverseLastMove();
                    }
                }
            } else {
                // get it up to date again
                Random rand = new Random();
                System.out.println("Simulation");
                for (int i = 0; i < simulatedMovesSelection.size(); i++) {
                    currentNode.state.movePiece(simulatedMovesSelection.get(i).getStart().getRow(),simulatedMovesSelection.get(i).getStart().getCol(),simulatedMovesSelection.get(i).getDestination().getRow() ,simulatedMovesSelection.get(i).getDestination().getCol(), true);
                }
                currentNode.state.diceRoll();
                currentNode.state.getBoard().printBoard();
                while (currentDepth <= depth){                           // Simulation
                    currentNode.getState().diceRoll();
                    int a = currentNode.getState().getPossibleMoves().size();
                    simulatedMove = currentNode.getState().getPossibleMoves().get(rand.nextInt(a));
                    simulatedMoves.add(simulatedMove);
                    currentNode.getState().movePiece(simulatedMove.getStart().getRow(), simulatedMove.getStart().getCol(), simulatedMove.getDestination().getRow(), simulatedMove.getDestination().getCol(), true);
                    currentDepth++;
                }
                currentNode.state.getBoard().printBoard();

                result = currentNode.getState().boardEvaluationFunc();
                for (int i = 0; i < simulatedMoves.size(); i++) {
                    currentNode.getState().reverseLastMove();
                }
                for (int i = 0; i < simulatedMovesSelection.size(); i++) {
                    currentNode.getState().reverseLastMove();
                }
                currentNode.state.getBoard().printBoard();

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
        currentNode = currentNode.children.get(0); // fix here + eval function
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
    public int maxIterations = 10;
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