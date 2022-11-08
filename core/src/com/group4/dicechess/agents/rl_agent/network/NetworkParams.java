package com.group4.dicechess.agents.rl_agent.network;

public enum NetworkParams {
    inputLength(8),
    inputChannels(6),
    targetUpdate(10),
    gamma(0.69),
    learningRate(0.001),
    kernelSize(3),
    outputLength(4),
    c1Filters(8),
    c2Filters(16),
    c3Filters(8),
    version("V1.0"),
    maxMoves(2000),
    batchSize(256);

    NetworkParams(int valueInt) {
        this.valueInt = valueInt;
    }

    NetworkParams(double valueDbl) {
        this.valueDbl = valueDbl;
    }

    NetworkParams(String valueStr) { this.valueStr = valueStr; }

    public int valueInt;
    public double valueDbl;
    public String valueStr;
}
