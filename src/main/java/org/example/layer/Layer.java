package org.example.layer;


import java.util.ArrayList;
import java.util.List;

public abstract class Layer {

    protected Layer next;
    protected Layer prev;

    public abstract double[] getOutput(List<double[][]> inputs);
    public abstract double[] getOutput(double[] inputs);

    public abstract void backprop(double[] inputs);
    public abstract void backprop(List<double[][]> inputs);


    public double[] matrixToVector(List<double[][]> matrix) {
        int length = matrix.size();
        int rows = matrix.get(0).length;
        int cols = matrix.get(0)[0].length;

        int i = 0;

        double[] vector = new double[length*rows*cols];
        for (int l = 0; l < length; l++) {
            for (int y = 0; y < rows; y++) {
                for (int x = 0; x < cols; x++) {
                    vector[i] = matrix.get(l)[y][x];
                    i++;
                }
            }
        }


        return vector;
    }

    public List<double[][]> vectorToMatrix(double[] vector, int length, int rows, int cols) {
        List<double[][]> matrix = new ArrayList<double[][]>();

        int i = 0;

        for (int l = 0; l < length; l++) {
            double[][]item = new double[rows][cols];
            for (int y = 0; y < rows; y++) {
                for (int x = 0; x < cols; x++) {
                    item[y][x] = vector[i];
                    i++;
                }
            }

            matrix.add(item);
        }

        return matrix;

    }




}