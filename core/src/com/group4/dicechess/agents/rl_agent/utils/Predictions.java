package com.group4.dicechess.agents.rl_agent.utils;

public record Predictions(double[][] piecePrediction, double[][] movePrediction, double[][] validatedPieces, double[][] validatedMoves) {
}
