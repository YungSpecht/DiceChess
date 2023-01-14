package com.group4.dicechess.agents.NN_Evaluation.network;
public class FC_Layer {

    private FC_Neuron[] neurons;

    public final int numNeurons, numInputs;
    private final boolean activated;

    public FC_Layer(int numNeurons, int numInputs, boolean activated) {
        this.numNeurons = numNeurons;
        this.numInputs = numInputs;
        this.activated = activated;
        init_neurons();
    }

    public FC_Layer(int numNeurons, int numInputs) {
        this.numNeurons = numNeurons;
        this.numInputs = numInputs;
        this.activated = true;
        init_neurons();
    }

    public double[] forward(double[] input){
        double[] out = new double[numNeurons];

        for (int i = 0; i < numNeurons; i++)
            out[i] = neurons[i].forward(input);

        return out;
    }

    private void init_neurons(){
        neurons = new FC_Neuron[numNeurons];
        for (int i = 0; i < numNeurons; i++)
            neurons[i] = new FC_Neuron(numInputs, activated);
    }

    public void load_weights(double[][] weights){
        for (int i = 0; i < numNeurons; i++)
            neurons[i].load_weights(weights[i]);
    }

    public void load_bias(double[] biases){
        for (int i = 0; i < numNeurons; i++)
            neurons[i].load_bias(biases[i]);
    }


}
