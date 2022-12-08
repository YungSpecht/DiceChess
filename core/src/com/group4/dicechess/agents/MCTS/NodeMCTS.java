package com.group4.dicechess.agents.MCTS;

import com.group4.dicechess.GameState;
import com.group4.dicechess.Representation.Move;
import com.group4.dicechess.agents.basic_agents.Node;
import java.util.ArrayList;
import java.util.List;

public class NodeMCTS {

    public NodeMCTS parent;
    public Move previousMove;
    public List<NodeMCTS> children;
    public double mean_value;
    public int visited;
    public GameState state;


    public NodeMCTS(NodeMCTS parent, Move move, GameState state){
        this.parent = parent;
        this.children = new ArrayList<NodeMCTS>();
        this.previousMove = move;
        this.mean_value = 0.0;
        this.visited = 0;
        this.state = state;
    }

    public void update(double result){
        this.visited++;
        this.mean_value = (this.mean_value + result)/this.visited;
    }

    public GameState getState() {
        return state;
    }

    public boolean hasParent(){
        return parent != null;
    }

    public NodeMCTS getParent() {
        return parent;
    }

    public void setMean_value(int wins){
        this.mean_value = this.mean_value + wins;
    }

    public double getMean_value() {
        return this.mean_value;
    }

    public void setVisited(int visited){
        this.visited = this.visited + visited;
    }

    public int getVisited() {
        return visited;
    }
}