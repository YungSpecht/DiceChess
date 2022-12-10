package com.group4.dicechess.agents.Testing;

import com.group4.dicechess.GameState;
import com.group4.dicechess.Representation.Move;
import com.group4.dicechess.agents.Bot;
import com.group4.dicechess.agents.MCTS.MonteCarloTreeSearch;
import com.group4.dicechess.agents.basic_agents.ExpectimaxBot;
import com.group4.dicechess.agents.basic_agents.GreedyBot;
import com.group4.dicechess.agents.basic_agents.RandomBot;
import com.group4.dicechess.agents.rl_agent.RL_Agent;

/**
 * Class that allows Bot vs Bot gameplay.
 */

public class BotVsBot {

    private Bot botW;
    private Bot botB;
    private Bot tempBot;
    private RL_Agent rl_agent;
    private GameState game;
    private int BlackBot;
    private double BlackBotWin = 0;
    private int WhiteBot;
    private double WhiteBotWin = 0;
    private String bot1;
    private String bot2;
    private int numberOfGames;
    private Move currentMove;
    private int turnCounter;
    private int movesMade;
    private int BlackScore;
    private int WhiteScore;
    private double drawScore;
    private int drawDecider = 300;
    private int moveLimit = 80;

    public BotVsBot(int WhiteBot, int BlackBot, int numberOfGames){
        this.WhiteBot = WhiteBot;
        this.BlackBot = BlackBot;
        this.numberOfGames = numberOfGames;
        this.createBots();
    }

    public static void main(String[] args) {
        BotVsBot simulation = new BotVsBot(2, 3, 20);
        simulation.startSimulation();
    }

    public void startSimulation(){
        turnCounter = 0;
        movesMade = 0;
        //rl_agent = new RL_Agent(false, game);
        for (int i = 0; i < numberOfGames; i++) {
            game = new GameState();
            if(i == (numberOfGames/2)-1){
                tempBot = botW;
                botW = botB;
                botB = tempBot;
            }
            movesMade = 0;
            while(!game.gameOver() && movesMade < moveLimit){
                createBots();
                // White's turn.
                if(turnCounter % 2 == 0){
                    currentMove = botW.getMove();
                } // Black's turn.
                else{
                    currentMove = botB.getMove();
                }
                game.movePiece(currentMove.getStart().getRow(), currentMove.getStart().getCol(), currentMove.getDestination().getRow(), currentMove.getDestination().getCol(), true);
                turnCounter++;
                movesMade++;
            }
            String winner = turnCounter%2==0 ? "Black" : "White";
            if(game.gameOver()){
                if(i < (numberOfGames/2)-1){
                    if(winner.equals("White")){
                        WhiteBotWin++;
                    }else {
                        BlackBotWin++;
                    }
                }else{
                    if(winner.equals("White")){
                        BlackBotWin++;
                    }else {
                        WhiteBotWin++;
                    }
                }
            }else {
                BlackScore = game.evaluateMCTS();
                WhiteScore = game.evaluate();
                if(Math.abs(WhiteScore-BlackScore)>drawDecider){
                    winner = ((WhiteScore>BlackScore)) ? "Black" : "White";
                    if(i < (numberOfGames/2)-1){
                        if(winner.equals("White")){
                            WhiteBotWin++;
                        }else {
                            BlackBotWin++;
                        }
                    }else{
                        if(winner.equals("White")){
                            BlackBotWin++;
                        }else {
                            WhiteBotWin++;
                        }
                    }
                }else {
                    drawScore++;
                }
            }
        }
        System.out.println(WhiteBotWin);
        System.out.println(BlackBotWin);
        getWinRate();
    }

    public void getWinRate(){
        double Whitewinrate =(WhiteBotWin/(WhiteBotWin+BlackBotWin+drawScore))*100;
        double BlackWinrate =(BlackBotWin/(WhiteBotWin+BlackBotWin+drawScore))*100;
        double DrawRate =(drawScore/(WhiteBotWin+BlackBotWin+drawScore))*100;

        System.out.println("Winrate for " + bot1 + " is: " + Whitewinrate + "%");
        System.out.println("Winrate for " + bot2 + " is: " + BlackWinrate + "%");
        System.out.println("Draw rate: " + DrawRate + "%");
    }

    public void createBots(){
        switch(WhiteBot){
            case 1:
                botW = new RandomBot(game);
                bot1 = "Random bot";
                break;
            case 2:
                botW = new GreedyBot(game);
                bot1 = "Greedy bot";
                break;
            case 3:
                botW = new ExpectimaxBot(game);
                bot1 = "Expectimax bot";
                break;
            case 4:
                botW = new MonteCarloTreeSearch(game);
                bot1 = "MCTS bot";
                break;
            case 5:
                botW = rl_agent;
                bot1 = "RL agent";
                break;
        }
        switch(BlackBot){
            case 1:
                botB = new RandomBot(game);
                bot2 = "Random bot";
                break;
            case 2:
                botB = new GreedyBot(game);
                bot2 = "Greedy bot";
                break;
            case 3:
                botB = new ExpectimaxBot(game);
                bot2 = "Expectimax bot";
                break;
            case 4:
                botB = new MonteCarloTreeSearch(game);
                bot2 = "MCTS bot";
                break;
            case 5:
                botB = rl_agent;
                bot2 = "RL agent";
                break;
        }
    }
}
