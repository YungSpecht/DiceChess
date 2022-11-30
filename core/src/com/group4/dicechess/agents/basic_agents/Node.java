package com.group4.dicechess.agents.basic_agents;

import java.util.ArrayList;
import java.util.List;

import com.group4.dicechess.GameState;

public class Node{
    private GameState state;
    private Node parent;
    private List<Node> children;
    private double value;
    private Node bestNextState;

    public Node(GameState state){
        this.state = state;
        children = new ArrayList<Node>();
        value = 0;
        parent = null;
        bestNextState = null;
    }
    
    public GameState getGameState(){
        return state;
    }

    public double getValue(){
        return value;
    }

    public void setParent(Node parent){
        this.parent = parent;
    }

    public void addChild(Node child){
        children.add(child);
    }

    public Node getParent(){
        return parent;
    }

    public boolean isChanceNode(){
        return state.getDiceRoll() != 0;
    }

    public List<Node> getChildren(){
        return children;
    }

    public boolean isMin(){
        return false;
    }

    public Node getBestNextState(){
        return bestNextState;
    }

    public void computeValue(){
        if(this.isChanceNode()){
            for(Node n : children){
                value += n.getValue();
            }
            value /= children.size();
        }
        else{
            if(state.getTurnCounter() % 2 == 0){
                for(Node n : children){
                    if(n.getValue() > value){
                        value = n.getValue();
                        bestNextState = n;
                    }
                }
            }
            else{
                value = Double.MAX_VALUE;
                for(Node n : children){
                    if(n.getValue() < value){
                        value = n.getValue();
                        bestNextState = n;
                    }
                }
            }
        }
    }

    public void evaluateNode(){
        return;
    }
}
