package org.example.dataset;

public class Image {
    double[][] data;
    int label;

    public double[][] getImage() {
        return this.data;
    }

    public int getLabel() {
        return this.label;
    }

    public Image(double[][] data, int label) {
        this.data = data;
        this.label = label;
    }

}
