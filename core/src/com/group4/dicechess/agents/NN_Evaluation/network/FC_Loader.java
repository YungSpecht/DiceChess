package com.group4.dicechess.agents.NN_Evaluation.network;

import com.group4.dicechess.agents.rl_agent.network.Network;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class FC_Loader {

    private static final String split = ",";
    private static final String PATH = "network_weights/";

    public static void load_network(FC_Network network) throws Exception {

        FC_Layer[] layers = network.getFcLayers();

        for (int i = 0; i < layers.length; i++) {
            load_layer(layers[i], i);
        }

        System.out.println("Load Successful");
    }

    private static void load_layer(FC_Layer layer, int number){
        try {
            FileReader weights_file = new FileReader(PATH + "weights_" + number + ".csv");
            BufferedReader weights_reader = new BufferedReader(weights_file);

            load_weights(layer, weights_reader);

            weights_reader.close();
            weights_file.close();

            if (number==2) {
                layer.load_bias(new double[]{-1.2127883564971853e-05});
                return;
            }

            FileReader bias_file = new FileReader(PATH + "bias_" + number + ".csv");
            BufferedReader bias_reader = new BufferedReader(bias_file);

            load_bias(layer, bias_reader);

            bias_reader.close();
            bias_file.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void load_weights(FC_Layer layer, BufferedReader reader) {
        String line;
        String[] splitLine;
        double[][] weights = new double[layer.numNeurons][layer.numInputs];

            try {
                reader.readLine();
                for (int i = 0; i < layer.numNeurons; i++) {
                    line = reader.readLine();
                    splitLine = line.split(split);
                    for (int j = 0; j < layer.numInputs; j++) {
                        weights[i][j] = Double.parseDouble(splitLine[j + 1]);
                    }
                    //weights[i] = Arrays.stream(splitLine).mapToDouble(Double::parseDouble).toArray();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        layer.load_weights(weights);
    }

    private static void load_bias(FC_Layer layer, BufferedReader reader) {
        String[] splitLine;
        double[] biases = new double[layer.numNeurons];

        try {
            reader.readLine();
            splitLine = reader.readLine().split(split);
            for (int i = 0; i < layer.numNeurons; i++) {
                biases[i] = Double.parseDouble(splitLine[i+1]);
            }
            //biases = Arrays.stream(splitLine).mapToDouble(Double::parseDouble).toArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        layer.load_bias(biases);
    }
}
