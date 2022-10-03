package com.group4.dicechess;

public class Game {
    private Board board;
    private int turnCounter;
    private boolean gameOver;
    private int whiteScore;
    private int blackScore;

    public Game(){
        board = new Board();
        turnCounter = 0;
        whiteScore = 0;
        blackScore = 0;
        gameOver = false;
    }

    public void startGame(){
        while(!gameOver){
            
        }
    }
}
