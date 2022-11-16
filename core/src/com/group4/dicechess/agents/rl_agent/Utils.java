package com.group4.dicechess.agents.rl_agent;

public class Utils {


    public static int argmax(double[] input){
        int argmax = 0;

        for (int i = 0; i < input.length; i++)
            argmax = input[i] > input[argmax] ? i : argmax;

        return argmax;
    }


    public static int[] argmax2D(double[][] input){

        int[] out = new int[2];
        double max = Double.NEGATIVE_INFINITY;

        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[0].length; j++) {

                if (input[i][j] < max) continue;

                out[0] = i;
                out[1] = j;
                max = input[i][j];

            }
        }

        return out;
    }

    public static double[][] unFlatten(double[] input){
        return unFlatten(input, 8);
    }

    public static double[][] unFlatten(double[] input, int length){

        double[][] out = new double[length][length];
        int ind = 0;

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                out[i][j] = input[ind++];
            }
        }

        return out;
    }

    public static double[][] dotProduct(double[][] matA, double[][] matB){
        assert matA.length == matB.length : "Unequal length matrices";
        assert matA[0].length == matB[0].length : "Unequal length matrices";

        double[][] out = new double[matA.length][matA[0].length];

        for (int i = 0; i < matA.length; i++) {
            for (int j = 0; j < matA.length; j++) {
                out[i][j] = matA[i][j] * matB[i][j];
            }
        }

        return out;
    }

}
