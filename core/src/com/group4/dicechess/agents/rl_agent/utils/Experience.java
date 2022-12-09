package com.group4.dicechess.agents.rl_agent.utils;

public record Experience (double[][][] state,
                          Predictions predictions,
                          Action action,
                          int pieceId,
                          double reward) {

    public Experience setReward(double reward){
        return new Experience(state,predictions, action, pieceId, reward);
    }
}
