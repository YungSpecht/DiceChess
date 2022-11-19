package com.group4.dicechess.agents.rl_agent.utils;

public record Experience (double[][] piecePrediction,
                          double[][] movePrediction,
                          double[][] validatedPieces,
                          double[][] validatedMoves,
                          Action action,
                          int pieceId,
                          double reward) {

    public Experience setReward(double reward){
        return new Experience(piecePrediction, movePrediction, validatedPieces, validatedMoves, action, pieceId, reward);
    }
}
