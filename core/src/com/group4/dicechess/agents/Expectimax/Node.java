package com.group4.dicechess.agents.Expectimax;

import java.util.ArrayList;
import java.util.List;

import com.group4.dicechess.GameState;

public class Node{
    private Node parent;
    private final List<Node> children;
    private double value;
    private final boolean chanceNode;


    public Node(GameState state) {
        children = new ArrayList<Node>();
        value = 0;
        chanceNode = state.getDiceRoll() == 0;
        parent = null;
    }
    

    public double getValue(){
        return value;
    }

    public void setValue(double value){
        this.value = value;
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
        return chanceNode;
    }

    public List<Node> getChildren(){
        return children;
    }


}
