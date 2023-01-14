package com.group4.dicechess.agents.NN_Evaluation.network;

public class FC_Neuron {

    private final int numInputs;
    private final boolean activated;
    private double[] weights;
    private double bias;
    public FC_Neuron(int numInputs, boolean activated){
        this.numInputs = numInputs;
        this.activated = activated;


    }

    public double forward(double[] input){

        double out = bias;

        for (int i = 0; i < numInputs; i++)
            out += input[i] * weights[i];

        if (activated)
            return Math.max(0, out);

        return out;
    }

    public void load_weights(double[] weights){
        this.weights = weights;
    }

    public void load_bias(double bias){
        this.bias = bias;
    }
}
