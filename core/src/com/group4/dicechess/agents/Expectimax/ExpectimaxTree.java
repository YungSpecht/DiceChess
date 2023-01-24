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
    private static int counter;
    private final boolean nnEval;

    public Node getRoot(){
        return root;
    }

    public ExpectimaxTree(GameState currentState, boolean nnEval){
        root = new Node(currentState);
        state = currentState;
        possibleOutcomes = state.getPossibleMoves();
        this.nnEval = nnEval;
    }

    public static void main(String[] args) {
        GameState game = new GameState();
        game.setDiceRoll(1);
        ExpectimaxBot bot = new ExpectimaxBot(game);
        long checkpoint = System.currentTimeMillis();
        bot.getMove();
        long time = System.currentTimeMillis() - checkpoint;
        System.out.println("Leaf Nodes: " + counter);
        System.out.println("Time: " + time + "ms");
    }

    public void buildTree(int depth){
        starMinMax(root, state, depth, -20000, 20000);
    }

    private void starMinMax(Node pointer, GameState game,  int depth, double alpha, double beta){
        if(depth <= 0 || isTerminal(pointer, game)){
            if (nnEval)
                pointer.setValue(NN_Evaluation.evaluate(game.getBoard(), game.turnCounter % 2 == 0));
            else pointer.setValue(game.evaluate());
            counter++;
        }
        else if(pointer.isChanceNode()){
            ArrayList<Integer> r = new ArrayList<Integer>(game.getRollsList());
            boolean cutoff = false;
            double L = -19455;
            double U = 19455;
            double N = r.size();
            double vsum = 0;
            double A = N * (alpha - U) + U;
            double B = N * (beta - L) + L;
            for(int i = 0; i < N; i++){
                double AX = Math.max(A, L);
                double BX = Math.min(B, U);
                game.setDiceRoll(r.get(i));
                Node childNode = new Node(game);
                childNode.setParent(pointer);
                pointer.addChild(childNode);
                starMinMax(childNode, game, depth-1, AX, BX);
                double v = childNode.getValue();
                vsum += v;
                if(v <= A){
                    vsum += U * (N - (i + 1));
                    pointer.setValue(vsum / N);
                    cutoff = true;
                    break;
                }
                if(v >= B){
                    vsum += L * (N - (i + 1));
                    pointer.setValue(vsum / N);
                    cutoff = true;
                    break;
                }
                A += U - v;
                B += L - v;
            }
            game.setDiceRoll(0);
            if(!cutoff){
                pointer.setValue(vsum/r.size());
            }
        }
        else{
            ArrayList<Move> pM = new ArrayList<Move>(game.getPossibleMoves());
            double value;
            if(game.getTurnCounter() % 2 == 0){
                value = -20000;
                for(Move m : pM){
                    game.movePiece(m.getStart().getRow(), m.getStart().getCol(), m.getDestination().getRow(), m.getDestination().getCol(), true);
                    Node childNode = new Node(game);
                    childNode.setParent(pointer);
                    pointer.addChild(childNode);
                    starMinMax(childNode, game, depth-1, alpha, beta);
                    game.reverseLastMove();
                    if(childNode.getValue() > value){
                        value = childNode.getValue();
                        pointer.setBestNextNode(childNode);
                    }
                    if(value > beta){
                        break;
                    }
                    alpha = Math.max(alpha, value);
                }
            }
            else{
                value = 20000;
                for(Move m : pM){
                    game.movePiece(m.getStart().getRow(), m.getStart().getCol(), m.getDestination().getRow(), m.getDestination().getCol(), true);
                    Node childNode = new Node(game);
                    childNode.setParent(pointer);
                    pointer.addChild(childNode);
                    starMinMax(childNode, game, depth-1, alpha, beta);
                    game.reverseLastMove();
                    if(childNode.getValue() < value){
                        value = childNode.getValue();
                        pointer.setBestNextNode(childNode);
                    }
                    if(value < alpha){
                        break;
                    }
                    beta = Math.min(beta, value);
                }
            }
            pointer.setValue(value);
        }
    }

    private boolean isTerminal(Node node, GameState game){
        if(node.isChanceNode() && game.getRollsList().size() == 0){
            return true;
        }
        else return !node.isChanceNode() && game.getPossibleMoves().size() == 0;
    }



    public Move getBestMove(){
        int idx = root.getChildren().indexOf(root.getbestNextNode());
        return possibleOutcomes.get(idx);
    }
}