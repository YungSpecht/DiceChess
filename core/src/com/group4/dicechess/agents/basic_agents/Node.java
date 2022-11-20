package com.group4.dicechess.agents.basic_agents;

import java.util.List;

import com.group4.dicechess.GameState;

public class Node{
    private GameState state;
    private Node parent;
    private List<Node> children;

    public Node(GameState state){
        this.state = state;
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
}
