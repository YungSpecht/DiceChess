package com.group4.dicechess.agents.NN_Evaluation.network;

import com.group4.dicechess.agents.NN_Evaluation.network.FC_Layer;
import com.group4.dicechess.agents.rl_agent.FCN.ActivationLayer;
import com.group4.dicechess.agents.rl_agent.FCN.DenseLayer;


public class FC_Network {

    private final FC_Layer[] fcLayers;

    public FC_Network(){

        fcLayers = new FC_Layer[]{
                new FC_Layer(2048, 768),
                new FC_Layer(2048, 2048),
                new FC_Layer(1, 2048, false)};

    }

    public double forward(double[] input){

        for (FC_Layer layer : fcLayers)
            input = layer.forward(input);

        return input[0];
    }

    public void backward(double[] error){
        for (FC_Layer layer : fcLayers)
            error = layer.backward(error);
    }

    public void update_network(){
        for (FC_Layer layer : fcLayers)
            layer.update_layer();
    }



    public FC_Layer[] getFcLayers() {
        return fcLayers;
    }
}
