package com.group4.dicechess.agents.rl_agent;

public record Experience (double[] piecePrediction, double[] movePrediction, int pieceId) {
}
