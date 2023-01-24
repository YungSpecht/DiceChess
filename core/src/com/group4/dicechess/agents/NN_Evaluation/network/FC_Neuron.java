package com.group4.dicechess.agents.NN_Evaluation.network;

public class FC_Neuron {

    private final int numInputs;
    private final boolean activated;
    private double[] weights, weights_gradient;
    private double bias, bias_gradient;
    private final double learning_rate;
    public FC_Neuron(int numInputs, boolean activated){
        this.numInputs = numInputs;
        this.activated = activated;
        this.weights_gradient = new double[numInputs];
        this.bias_gradient = 0;
        this.learning_rate = 0.99;
    }

    public double forward(double[] input){

        double out = bias;

        for (int i = 0; i < numInputs; i++)
            out += input[i] * weights[i];

        if (activated)
            return Math.max(0, out);

        return out;
    }

    public double[] backward(double[] input, double error){

        double[] input_gradient = new double[numInputs];

        for (int i = 0; i < numInputs; i++) {
            weights_gradient[i] += error * input[i];
            input_gradient[i] = error * weights[i];
        }

        bias_gradient += error;

        return input_gradient;
    }

    public void update_neuron(){

        for (int i = 0; i < numInputs; i++)
            weights[i] -= learning_rate * weights_gradient[i];

        bias -= learning_rate * bias_gradient;

        weights_gradient = new double[numInputs];
        bias_gradient = 0;
    }

    public void load_weights(double[] weights){
        this.weights = weights;
    }

    public void load_bias(double bias){
        this.bias = bias;
    }
}
