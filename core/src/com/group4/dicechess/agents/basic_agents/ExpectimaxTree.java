package com.group4.dicechess.agents.basic_agents;

import java.util.ArrayList;

import com.group4.dicechess.GameState;
import com.group4.dicechess.Representation.Move;
import com.group4.dicechess.agents.NN_Evaluation.NN_Evaluation;

public class ExpectimaxTree {
    private final Node root;
    private final GameState state;
    private final ArrayList<Move> possibleOutcomes;
    private final double L = 0;
    private final double U = 10;


    public Node getRoot(){
        return root;
    }

    public ExpectimaxTree(GameState currentState){
        new NN_Evaluation();
        root = new Node(currentState);
        state = currentState;
        possibleOutcomes = state.getPossibleMoves();
    }

    public void buildTree(int depth){
        starMinMax(root, state, depth, Double.MIN_VALUE, Double.MAX_VALUE);
    }

    private void starMinMax(Node pointer, GameState game,  int depth, double alpha, double beta){
        if(depth <= 0) {
            pointer.setValue(game.evaluate());
        }
        else if(pointer.isChanceNode()){
            boolean cutoff = false;
            ArrayList<Integer> rolls = game.getRollsList();
            ArrayList<Integer> r = new ArrayList<Integer>(rolls);
            double A = rolls.size() * (alpha - U) + U;
            double B = rolls.size() * (beta - L) + L;
            double vsum = 0;
            for(int i = 0; i < rolls.size(); i++){
                double AX = Math.max(A, L);
                double BX = Math.min(B, U);
                game.setDiceRoll(rolls.get(i));
                Node childNode = new Node(game);
                childNode.setParent(pointer);
                pointer.addChild(childNode);
                starMinMax(childNode, game,depth-1, AX, BX);
                vsum += childNode.getValue();
                if(childNode.getValue() <= A){
                    vsum += U * (rolls.size() - (i + 1));
                    pointer.setValue(vsum/rolls.size());
                    cutoff = true;
                    break;
                }
                if(childNode.getValue() >= B){
                    vsum += L * (rolls.size() - (i + 1));
                    pointer.setValue(vsum/rolls.size());
                    cutoff = true;
                    break;
                }
                A += U - childNode.getValue();
                B += L - childNode.getValue();
            }
            game.setDiceRoll(0);
            if(pointer.getChildren().size() == 0){
                pointer.setValue(game.evaluate());
            }
            else if(!cutoff){
                pointer.setValue(vsum/rolls.size());
            }
        }
        else{
            ArrayList<ArrayList<Move>> mL = game.getMoveList();
            ArrayList<Move> possibleMoves = game.getPossibleMoves();
            ArrayList<Move> pM = new ArrayList<Move>(possibleMoves);
            double value;
            if(game.turnCounter % 2 == 0){
                value = Double.MIN_VALUE;
                for(Move m : pM){
                    game.movePiece(m.getStart().getRow(), m.getStart().getCol(), m.getDestination().getRow(), m.getDestination().getCol(), true);
                    Node childNode = new Node(game);
                    childNode.setParent(pointer);
                    pointer.addChild(childNode);
                    starMinMax(childNode, game, depth-1, alpha, beta);
                    game.reverseLastMove();
                    game.setMoveList(mL);
                    value = Math.max(value, childNode.getValue());
                    if(value > beta){
                        break;
                    }
                    alpha = Math.max(alpha, value);
                }
            }
            else{
                value = Double.MAX_VALUE;
                for(Move m : pM){
                    game.movePiece(m.getStart().getRow(), m.getStart().getCol(), m.getDestination().getRow(), m.getDestination().getCol(), true);
                    Node childNode = new Node(game);
                    childNode.setParent(pointer);
                    pointer.addChild(childNode);
                    starMinMax(childNode, game, depth-1, alpha, beta);
                    game.reverseLastMove();
                    game.setMoveList(mL);
                    value = Math.min(value, childNode.getValue());
                    if(value < alpha){
                        break;
                    }
                    beta = Math.min(beta, value);
                }
            }
            if(pointer.getChildren().size() == 0){
                pointer.setValue(game.evaluate());
            }
            else{
                pointer.setValue(value);
            }
        }
    }

    public Move getBestMove(){
        double value = state.turnCounter % 2 == 0 ? Double.MIN_VALUE : Double.MAX_VALUE;
        int idx = 0;
        if(state.turnCounter % 2 == 0){
            for(Node child : root.getChildren()){
                if (child.getValue() > value) {
                    value = child.getValue();
                    idx = root.getChildren().indexOf(child);
                }
            }
        }
        else{
            for(Node child : root.getChildren()){
                if (child.getValue() < value) {
                    value = child.getValue();
                    idx = root.getChildren().indexOf(child);
                }
            }
        }
        return possibleOutcomes.get(idx);
    }

}
