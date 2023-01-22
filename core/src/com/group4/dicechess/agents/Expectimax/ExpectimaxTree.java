package com.group4.dicechess.agents.Expectimax;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.group4.dicechess.GameState;
import com.group4.dicechess.Representation.Move;
import com.group4.dicechess.agents.NN_Evaluation.NN_Evaluation;

public class ExpectimaxTree {
    private final Node root;
    private final GameState state;
    private final ArrayList<Move> possibleOutcomes;


    public Node getRoot(){
        return root;
    }

    public ExpectimaxTree(GameState currentState){
        //new NN_Evaluation();
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
            double u = 19455;
            double A = r.size() * (alpha - u) + u;
            double l = -19455;
            double B = r.size() * (beta - l) + l;
            double vsum = 0;
            for(int i = 0; i < r.size(); i++){
                double AX = Math.max(A, l);
                double BX = Math.min(B, u);
                game.setDiceRoll(r.get(i));
                Node childNode = new Node(game);
                childNode.setParent(pointer);
                pointer.addChild(childNode);
                starMinMax(childNode, game,depth-1, AX, BX);
                vsum += childNode.getValue();
                if(childNode.getValue() <= A){
                    vsum += u * (r.size() - (i + 1));
                    pointer.setValue(vsum/r.size());
                    cutoff = true;
                    break;
                }
                if(childNode.getValue() >= B){
                    vsum += l * (r.size() - (i + 1));
                    pointer.setValue(vsum/r.size());
                    cutoff = true;
                    break;
                }
                A += u - childNode.getValue();
                B += l - childNode.getValue();
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
            //sortMoveList(pM, game);
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

    private void sortMoveList(ArrayList<Move> moves, GameState game){
        ArrayList<Double> values = new ArrayList<Double>();
        ArrayList<Move> result = new ArrayList<Move>();
        for(Move m : moves){
            game.movePiece(m.getStart().getRow(), m.getStart().getCol(), m.getDestination().getRow(), m.getDestination().getCol(), true);
            values.add((double) game.evaluate());
            game.reverseLastMove();
        }
        int size = values.size();
        for(int i = 0; i < size; i++){
            double val = Double.MAX_VALUE;
            int idx = 0;
            for(int j = 0; j < moves.size(); j++) {
                if (values.get(j) < val) {
                    val = values.get(j);
                    idx = j;
                }
            }
            result.add(moves.get(idx));
            moves.remove(idx);
            values.remove(idx);
        }
        if(game.getTurnCounter() % 2 == 0){
            Collections.reverse(result);
        }
        moves = result;
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
