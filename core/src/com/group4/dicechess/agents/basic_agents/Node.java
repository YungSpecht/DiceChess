package com.group4.dicechess.agents.basic_agents;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.group4.dicechess.GameState;
import com.group4.dicechess.agents.NN_Evaluation.NN_Evaluation;

public class Node{
    private Node parent;
    private List<Node> children;
    private double value;
    private Node bestNextNode;
    private boolean chanceNode;
    private int turnCount;


    public Node(GameState state) {
        children = new ArrayList<Node>();
        value = 0;
        chanceNode = state.getDiceRoll() == 0;
        turnCount = state.getTurnCounter();
        parent = null;
        bestNextNode = null;
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
        return chanceNode;
    }

    public List<Node> getChildren(){
        return children;
    }

    public Node getbestNextNode(){
        return bestNextNode;
    }

    public void computeValue(GameState state){
        if(children.size() == 0){
            value = NN_Evaluation.evaluate(state.getBoard());
            // value = state.evaluate();
        }
        else{
            if(chanceNode){
                for(Node n : children){
                    value += n.getValue();
                }
                value /= children.size();
            }
            else{
                ArrayList<Node> equalNodes = new ArrayList<Node>();
                equalNodes.add(children.get(0));
                value = children.get(0).getValue();
                if(turnCount % 2 == 0){
                    for(Node n : children){
                        if(n.getValue() > value){
                            value = n.getValue();
                            bestNextNode = n;
                            equalNodes.clear();
                            equalNodes.add(n);
                        }
                        else if(n.getValue() == value){
                            equalNodes.add(n);
                        }
                    }
                    
                }
                else{
                    for(Node n : children){
                        if(n.getValue() < value){
                            value = n.getValue();
                            bestNextNode = n;
                            equalNodes.clear();
                            equalNodes.add(n);
                        }
                        else if(n.getValue() == value){
                            equalNodes.add(n);
                        }
                    }
                }
                Random rand = new Random();
                bestNextNode = equalNodes.get(rand.nextInt(equalNodes.size()));
            }
        }
    }

}
