package org.example.layer;

import java.util.List;
import java.util.Random;

public class Hidden extends Layer {

    private long SEED;
    private double[][] weights;
    private int input_length;
    private int output_length;
    private double[] z;
    double learning_rate;
    double[] lastInput;
    private final double leak = 0.1;

    public Hidden(int input_length, int output_length, long SEED, double learning_rate) {
        this.input_length = input_length;
        this.output_length = output_length;
        this.SEED = SEED;
        this.learning_rate = learning_rate;

        this.weights = new double[input_length][output_length];
        randomWeights();
    }

    public double[] forward(double[] input) {
        lastInput = input;
        double[] out = new double[output_length];

        for (int i = 0; i < input_length; i++) {
            for (int j = 0; j < output_length; j++) {
                out[j] += input[i] * weights[i][j];
            }
        }
        this.z = out;

        return relu(out);
    }

    @Override
    public double[] getOutput(List<double[][]> inputs) {
        double[] vector = matrixToVector(inputs);
        return getOutput(vector);
    }

    @Override
    public double[] getOutput(double[] inputs) {
        double[] fowardPass = forward(inputs);
        if (next != null)
        {
            fowardPass = next.getOutput(fowardPass);
        }

        return fowardPass;
    }

    @Override
    public void backprop(double[] inputs) {

        double[] dldx = new double[input_length];

        double d0dz;
        double dzdw;
        double dldw;
        double dzdx;

        for (int i = 0; i < input_length; i++) {

            double dldx_sum = 0;

            for (int j = 0; j < output_length; j++) {

                d0dz = deriveRelu(this.z[j]);
                dzdw = lastInput[i];
                dzdx = weights[i][j];

                dldw = inputs[j]*d0dz*dzdw;

                weights[i][j] -= dldw * learning_rate;

                dldx_sum += inputs[j]*d0dz*dzdx;
            }
        }

        if(prev != null) {
            prev.backprop(dldx);
        }

    }

    @Override
    public void backprop(List<double[][]> inputs) {
        double[] vector = matrixToVector(inputs);
        backprop(vector);
    }

    private void randomWeights() {
        Random random = new Random(SEED);
         for (int y = 0; y < input_length; y++) {
             for (int x = 0; x < output_length; x++) {
                 weights[x][y] = random.nextDouble();
             }
         }

    }

    private double[] relu(double[] input) {
        double[] out = new double[input_length];
        for(int i = 0; i < input.length; i++) {
           out[i] = Math.max(0, input[i]);
        }
        return out;
    }

    private double deriveRelu(double input) {
        if (input < 0) {
            return leak;
        }
        else {
            return 1;
        }
    }
}
