package com.group4.dicechess.agents.MCTS;

import com.group4.dicechess.GameState;
import com.group4.dicechess.Representation.Move;
import com.group4.dicechess.Representation.Piece;
import com.group4.dicechess.agents.Bot;
import com.group4.dicechess.agents.basic_agents.GreedyBot;
import com.group4.dicechess.agents.basic_agents.RandomBot;
import java.util.ArrayList;
import java.util.List;

public class MonteCarloTreeSearch implements Bot {

    public MonteCarloTreeSearch(GameState state){
        this.state = state;
        this.diceRollResult = 0;
    }

    public Move createMCTSTree(){
        this.state.diceRoll();
        NodeMCTS root = new NodeMCTS(null, null, this.state);
        while(maxIterations >= currentIteration) {
            currentNode = root;
            if(root.children.size() == 1){
                return root.children.get(0).getMove();
            }
            ArrayList<Move> possibleMoves = currentNode.getState().getPossibleMoves();
            if (possibleMoves.get(0).getPiece().getWhiteStatus()) {
                eval = true;
            } else{
                eval = false;
            }
            currentDepth = 0;
            flag = false;
            simulatedMoves = new ArrayList<>();
            simulatedMovesSelection = new ArrayList<>();
            simulatedMovesSelectionDiceRolls = new ArrayList<>();
            selection();
            if(!expansion(root)){
                simulation();
                backpropagation();
                currentIteration++;
            }
        }
        currentNode = root;
        int mostVisits = 0;
        int finalMove = 0;
        for (int i = 0; i < root.children.size(); i++) {
            if(root.children.get(i).getVisited() > mostVisits){
                mostVisits = root.children.get(i).getVisited();
                finalMove = i;
            }
        }
        currentNode = currentNode.children.get(finalMove);
        return currentNode.getMove();
    }

    public void selection(){
        while (!currentNode.children.isEmpty()) {
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
    }

    public boolean expansion(NodeMCTS root){
        if(currentNode.getVisited() != 0 || currentNode == root ) {
            if (currentNode.state.getRollsList().size() == 0) {
                System.out.println("OUT 1");
                currentIteration = 1000000;
            } else {
                if (currentNode != root) {
                    for (int i = 0; i < simulatedMovesSelection.size(); i++) {
                        currentNode.state.setDiceRoll(simulatedMovesSelectionDiceRolls.get(i));
                        currentNode.state.movePiece(simulatedMovesSelection.get(i).getStart().getRow(), simulatedMovesSelection.get(i).getStart().getCol(), simulatedMovesSelection.get(i).getDestination().getRow(), simulatedMovesSelection.get(i).getDestination().getCol(), true);
                    }
                    currentNode.state.diceRoll();
                }
                ArrayList<Move> possibleMoves = currentNode.getState().getPossibleMoves();
                ArrayList<Move> pM = new ArrayList<>(possibleMoves);
                for (Move m : pM) {
                    childNode = new NodeMCTS(currentNode, m, currentNode.state);
                    childNode.diceRoll = currentNode.state.getDiceRoll();
                    currentNode.children.add(childNode);
                }
                if (currentNode != root) {
                    for (int i = 0; i < simulatedMovesSelection.size(); i++) {
                        currentNode.getState().reverseLastMove();
                    }
                }
            }
            return true;
        }
        return false;
    }

    public void simulation(){
        for (int i = 0; i < simulatedMovesSelection.size(); i++) {
            currentNode.state.setDiceRoll(simulatedMovesSelectionDiceRolls.get(i));
            currentNode.state.movePiece(simulatedMovesSelection.get(i).getStart().getRow(),simulatedMovesSelection.get(i).getStart().getCol(),simulatedMovesSelection.get(i).getDestination().getRow() ,simulatedMovesSelection.get(i).getDestination().getCol(), true);
        }
        currentNode.getState().setDiceRoll(0);
        boolean gameOver = false;
        while (currentDepth <= depth && currentNode.state.getRollsList().size() != 0 && !gameOver){
            //Changing simulation strategy
            //GreedyBot next = new GreedyBot(currentNode.getState());
            RandomBot next = new RandomBot(currentNode.getState());
            simulatedMove = next.getMove();
            currentNode.getState().movePiece(simulatedMove.getStart().getRow(), simulatedMove.getStart().getCol(), simulatedMove.getDestination().getRow(), simulatedMove.getDestination().getCol(), true);
            simulatedMoves.add(simulatedMove);
            currentDepth++;
            ArrayList<Piece> white = currentNode.state.board.getWhiteCaptured();
            ArrayList<Piece> black = currentNode.state.board.getBlackCaptured();
            for(int i = 0; i < white.size(); i++){
                if(white.get(i).getId().equals("K")){
                    if(eval){
                        result = -1000000;
                    } else{
                        result = 100000;
                    }
                    gameOver = true;
                }
            }
            for(int i = 0; i < black.size(); i++){
                if(black.get(i).getId().equals("K")){
                    if(eval){
                        result = 100000;
                    } else{
                        result = -1000000;
                    }
                    gameOver = true;
                }
            }
        }
        if(!gameOver){
            if(eval){
                result = currentNode.getState().evaluate();
            }else{
                result = currentNode.getState().evaluateMCTS();
            }
        }
        for (int i = 0; i < simulatedMoves.size(); i++) {
            currentNode.getState().reverseLastMove();
        }
        for (int i = 0; i < simulatedMovesSelection.size(); i++) {
            currentNode.getState().reverseLastMove();
        }
    }

    public void backpropagation(){
        while(currentNode.hasParent()){
            currentNode.update(result);
            currentNode = currentNode.parent;
        }
        currentNode.update(result);
    }

    // UCB - Upper confidence bounds formula - Exploration / Exploitation
    public double uct_formula(double mean_node_val, double small_n, double big_n){
        if(small_n == 0){
            return 100000000;
        }
        return mean_node_val + tunable_c*(Math.sqrt((Math.log(big_n))/(small_n)));
    }

    @Override
    public Move getMove() {return createMCTSTree();}
    @Override
    public int getRoll() {return this.diceRollResult;}

    ArrayList<Move> simulatedMoves;
    ArrayList<Move> simulatedMovesSelection;
    ArrayList<Integer> simulatedMovesSelectionDiceRolls;
    public GameState state;
    public Move simulatedMove;
    public NodeMCTS currentNode;
    public NodeMCTS childNode;
    public List<NodeMCTS> tempStorage;
    public double tunable_c = Math.sqrt(2);
    public double currentBestChild;
    public double temp;
    public double result;
    public boolean flag;
    public boolean eval = false;
    public int diceRollResult;
    public int maxIterations = 200;
    public int depth = 2;
    public int currentDepth;
    public int currentIteration;
}