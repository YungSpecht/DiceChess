package com.group4.dicechess.agents.Expectimax;

import java.util.ArrayList;

import com.group4.dicechess.GameState;
import com.group4.dicechess.Representation.Move;
import com.group4.dicechess.agents.NN_Evaluation.NN_Evaluation;

public class ExpectimaxTree {
    private final Node root;
    private final GameState state;
    private final ArrayList<Move> possibleOutcomes;

    public static void main(String[] args) {
        GameState test = new GameState();
        test.diceRoll();
        ExpectimaxTree tree = new ExpectimaxTree(test);
        tree.buildTree(12);
        System.out.println(tree.getBestMove().getPiece().getId());
    }

    public Node getRoot(){
        return root;
    }

    public ExpectimaxTree(GameState currentState){
        root = new Node(currentState);
        state = currentState;
        possibleOutcomes = state.getPossibleMoves();
        new NN_Evaluation();
    }

    public void buildTree(int depth){
        buildLevel(root, state, depth);
    }

    private void buildLevel(Node pointer, GameState game,  int depth){

        if(pointer.isChanceNode()){
            if(depth > 0 ){
                ArrayList<Integer> rolls = game.getRollsList();
                ArrayList<Integer> r = new ArrayList<Integer>(rolls);
                for(int roll : r){
                    game.setDiceRoll(roll);
                    Node childNode = new Node(game);
                    childNode.setParent(pointer);
                    pointer.addChild(childNode);
                    buildLevel(childNode, game, depth-1);
                }
                game.setDiceRoll(0);
            }
            pointer.computeValue(game);
        }
        else{
            if(depth > 0){
                ArrayList<Move> possibleMoves = game.getPossibleMoves();
                ArrayList<Move> pM = new ArrayList<Move>(possibleMoves);
                for(Move m : pM){
                    game.movePiece(m.getStart().getRow(), m.getStart().getCol(), m.getDestination().getRow(), m.getDestination().getCol(), true);
                    Node childNode = new Node(game);
                    childNode.setParent(pointer);
                    pointer.addChild(childNode);
                    buildLevel(childNode, game, depth-1);
                    game.reverseLastMove();
                }
            }
            pointer.computeValue(game);
        }
    }

    public Move getBestMove(){
        int idx = root.getChildren().indexOf(root.getbestNextNode());
        return possibleOutcomes.get(idx);
    }

}