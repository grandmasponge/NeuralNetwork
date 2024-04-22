package org.example.dataset;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Reader {
    private final int width = 28;
    private final int height = 28;

    public List<Image> readData(String fileName) {
        List<Image> images = new ArrayList<Image>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                int label = Integer.parseInt(tokens[0]);
                double[][] data = new double[width][height];
                int i = 1;

                for (int y = 0; y < height; y++) {
                    for (int x = 0; x < width; x++) {
                        data[x][y] = Double.parseDouble(tokens[i]);
                        i++;
                    }
                }

                images.add(new Image(data, label));

            }
        }
        catch (Exception e) {
            System.out.println(e);
        }

        return images;
    }

}
