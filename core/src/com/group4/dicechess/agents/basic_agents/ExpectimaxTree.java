package com.group4.dicechess.agents.basic_agents;

import java.util.ArrayList;

import com.group4.dicechess.GameState;
import com.group4.dicechess.Representation.Move;

public class ExpectimaxTree {
    private Node root;
    private GameState state;
    private ArrayList<Move> possibleOutcomes;

    public static void main(String[] args) {
        GameState test = new GameState();
        test.diceRoll();
        ExpectimaxTree tree = new ExpectimaxTree(test);
        tree.buildTree(2);
        System.out.println(tree.getBestMove().getPiece().getId());
    }

    public Node getRoot(){
        return root;
    }

    public ExpectimaxTree(GameState currentState){
        root = new Node(currentState);
        state = currentState;
        possibleOutcomes = state.getPossibleMoves();

    }

    public void buildTree(int depth){
        buildLevel(root, state, depth);
    }

    private void buildLevel(Node pointer, GameState game,  int depth){
        if(pointer.isChanceNode()){
            ArrayList<Integer> rolls = game.getRollsList();
            ArrayList<Integer> r = new ArrayList<Integer>();
            r.addAll(rolls);
            for(int roll : r){
                game.setDiceRoll(roll);
                Node childNode = new Node(game);
                childNode.setParent(pointer);
                pointer.addChild(childNode);
                buildLevel(childNode, game, depth);
            }
            pointer.computeValue(game);
        }
        else{
            if(depth > 0){
                ArrayList<Move> possibleMoves = game.getPossibleMoves();
                ArrayList<Move> pM = new ArrayList<Move>();
                pM.addAll(possibleMoves);
                for(Move m : pM){
                    game.addMoves(pM);
                    game.movePiece(m.getStart().getRow(), m.getStart().getCol(), m.getDestination().getRow(), m.getDestination().getCol());
                    game.setDiceRoll(0);
                    Node childNode = new Node(game);
                    childNode.setParent(pointer);
                    pointer.addChild(childNode);
                    buildLevel(childNode, game, --depth);
                    depth++;
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
