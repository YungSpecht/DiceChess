package com.group4.dicechess.agents.NN_Evaluation.network;
public class FC_Layer {

    private FC_Neuron[] neurons;

    public final int numNeurons, numInputs;
    private final boolean activated;
    private double[] input, output;

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
        this.input = input;
        output = new double[numNeurons];

        for (int i = 0; i < numNeurons; i++)
            output[i] = neurons[i].forward(input);

        return output;
    }

    public double[] backward(double[] error){

        double[] input_gradient = new double[numInputs];
        double[] neuron_gradient;

        for (int i = 0; i < numNeurons; i++) {
            neuron_gradient = neurons[i].backward(input, error[i]);
            for (int j = 0; j < numInputs; j++) {
                input_gradient[j] += neuron_gradient[j];
            }
        }

        return activated ? input_gradient : activated_gradient(input_gradient);
    }

    public void update_layer(){
        for (FC_Neuron neuron : neurons)
            neuron.update_neuron();
    }

    private double[] activated_gradient(double[] input_gradient){
        for (int i = 0; i < numInputs; i++)
            input_gradient[i] = output[i] < 0 ? 0 : input_gradient[i];

        return input_gradient;
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
