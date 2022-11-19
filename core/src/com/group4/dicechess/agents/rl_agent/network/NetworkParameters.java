package com.group4.dicechess.agents.rl_agent.network;

public enum NetworkParameters {
    inputLength(8),
    inputChannels(15),
    targetUpdate(10),
    gamma(0.69),
    learningRate(0.001),
    kernelSize(3),
    outputLength(64),
    c1Filters(32),
    c2Filters(128),
    c3Filters(32),
    version("V1.0"),
    maxMoves(2000),
    batchSize(256);

    NetworkParameters(int valueInt) {
        this.valueInt = valueInt;
    }

    NetworkParameters(double valueDbl) {
        this.valueDbl = valueDbl;
    }

    NetworkParameters(String valueStr) { this.valueStr = valueStr; }

    public int valueInt;
    public double valueDbl;
    public String valueStr;
}
