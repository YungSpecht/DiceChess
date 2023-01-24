package com.group4.dicechess.agents.rl_agent.utils;

import com.group4.dicechess.GameState;
import com.group4.dicechess.Representation.Board;

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

    public static double[] flatten(double[][] input){
        double[] out = new double[input.length * input[0].length];
        int ind = 0;

        for (double[] row : input) {
            for (double entry : row) {
                out[ind++] = entry;
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

    public static double sum(double[] input){
        double out = 0;
        for (double v : input)
            out += v;

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

    public static void printTurn(int pieceID, GameState gameState){
        String team = gameState.isWhitesTurn() ? "White" : "Black";
        print("Turn Number: " + gameState.turnCounter);
        print(team + " " + nameFromId(pieceID));
    }


    public static String nameFromId(int id){
        return switch (id){
            case 1 -> "Pawn";
            case 2 -> "Knight";
            case 3 -> "Bishop";
            case 4 -> "Rook";
            case 5 -> "Queen";
            case 6 -> "King";
            default -> throw new IllegalStateException("Unexpected value: " + id);
        };
    }

    public static boolean isZeroMatrix(double[][] input){

        for (double[] row : input) {
            for (double entry : row) {
                if (entry != 0)
                    return false;
            }
        }

        return true;
    }

    public static void printBoardStatic(Board board){
        board.printBoard();
    }

    public static void print(String string){
        System.out.println(string);
    }

}
