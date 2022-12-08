package com.group4.dicechess.agents.basic_agents;

import java.util.ArrayList;

import com.group4.dicechess.GameState;
import com.group4.dicechess.Representation.Move;

public class ExpectimaxTree {
    private Node root;

    public ExpectimaxTree(GameState currentState){
        root = new Node(currentState);
    }

    public void buildTree(int depth){
        buildLevel(root, depth);
    }

    private void buildLevel(Node pointer, int depth){
        GameState game = pointer.getGameState();
        if(pointer.isChanceNode()){
            ArrayList<Integer> rolls = game.getRollsList();
            for(int roll : rolls){
                GameState nextState = game.copy();
                nextState.setDiceRoll(roll);
                Node childNode = new Node(nextState);
                childNode.setParent(pointer);
                pointer.addChild(childNode);
                if(depth > 0){
                    buildLevel(childNode, --depth);
                }
                pointer.computeValue();;
            }
        }
        else{
            ArrayList<Move> possibleMoves = game.getPossibleMoves();
            for(Move m : possibleMoves){
                GameState nexState = game.copy();
                nexState.movePiece(m.getStart().getRow(), m.getStart().getCol(), m.getDestination().getRow(), m.getDestination().getCol());
                Node childNode = new Node(nexState);
                childNode.setParent(pointer);
                pointer.addChild(childNode);
                if(depth > 0){
                    buildLevel(childNode, --depth);
                }
                pointer.computeValue();
            }
        }
    }

    public Move getBestMove(){
        ArrayList <Move> moveHistory = root.getBestNextState().getGameState().getMoveHistory();
        return moveHistory.get(moveHistory.size());
    }
}
