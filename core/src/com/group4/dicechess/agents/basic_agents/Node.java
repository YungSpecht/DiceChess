package com.group4.dicechess.agents.basic_agents;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.group4.dicechess.GameState;
import com.group4.dicechess.Representation.Board;

public class Node{
    private Node parent;
    private List<Node> children;
    private int value;
    private Node bestNextNode;
    private boolean chanceNode;
    private int turnCount;

    public Node(GameState state){
        children = new ArrayList<Node>();
        value = 0;
        chanceNode = state.getDiceRoll() == 0;
        turnCount = state.getTurnCounter();
        parent = null;
        bestNextNode = null;
    }
    

    public int getValue(){
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

    public boolean isMin(){
        return false;
    }

    public Node getbestNextNode(){
        return bestNextNode;
    }

    public void computeValue(GameState state){
        if(children.size() == 0){
            evaluateNode(state);
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

    private void evaluateNode(GameState state){
        Board b = state.getBoard();
        value = 200*(b.count("K", true)-b.count("K", false))+9*(b.count("Q", true)-b.count("Q", false))+5*(b.count("R", true)-b.count("R", false))+3*(b.count("B", true)-b.count("B", false))+3*(b.count("N", true)-b.count("N", false))+1*(b.count("P", true)-b.count("P", false));
        /*  int result = 0;
        for(int i = 0; i < b.getWhitePieces().size(); i++) {
            result += b.getWhitePieces().get(i).getValue();
        }
        value = result; */
    }
}
