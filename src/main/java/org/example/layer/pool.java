package org.example.layer;

import java.util.ArrayList;
import java.util.List;

public class pool extends Layer {

    private final int step = 1;
    private final int size = 2;

    private int input_length;
    private int inRows;
    private int inCols;

    public pool(int input_length, int inRows, int inCols) {
        this.input_length = input_length;
        this.inRows = inRows;
        this.inCols = inCols;

    }

    public List<double[][]> poolFoward(List<double[][]> input) {
        List <double[][]> output = new ArrayList<double[][]>();



        return output;
    }

    public double max(double[][] input) {
        double max = 0;
        int height = input_length;
        int width = input_length;

         for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (input[y][x] > max) {
                    max = input[y][x];
                }
            }
        }
         return max;
    }



    @Override
    public double[] getOutput(List<double[][]> inputs) {
        return new double[0];
    }

    @Override
    public double[] getOutput(double[] inputs) {
        return new double[0];
    }

    @Override
    public void backprop(double[] inputs) {

    }

    @Override
    public void backprop(List<double[][]> inputs) {

    }
}
