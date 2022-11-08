package com.group4.dicechess.agents.rl_agent.CNN;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import static com.group4.dicechess.agents.rl_agent.CNN.Filter.scaleSubtract;


public class Kernel {
    private int size = 3;
    private double[][] weights;
    private transient SecureRandom random;

    public Kernel(double initWeight){

        this.random = new SecureRandom();

        double scale = Math.pow(10, -initWeight);
        weights = new double[size][size];

        for (int i=0; i<size; i++)
            for (int j=0; j<size; j++)
                weights[i][j] = scale * (random.nextInt(3) - 1);
    }


    public Kernel(double[][] weights){
        this.weights = weights;
    }


    public double[][] getWeights(){
        return weights;
    }

    /**
     * @param kernelGradient - derivative of the error with respect to the kernel
     * @param learningRate - learning rate of the network
     */
    public void updateWeights(double[][] kernelGradient, double learningRate){
        weights = scaleSubtract(weights, kernelGradient, learningRate);
    }

    public String saveKernel(){

        String line = "";
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                line += weights[i][j] + ",";


        return line;
    }

    public void loadKernel(double[] nWeights) {
        assert nWeights.length == size * size : "Kernel: Not enough weights to load";

        int ind = 0;
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                weights[i][j] = nWeights[ind++];
    }


    public Kernel clone(){
        return new Kernel(weights.clone());
    }

}
