package util;

import model.DataAndSolutions;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DataReader {
    public DataAndSolutions readData(String filepath) {
        DataAndSolutions dataAndSolutions = new DataAndSolutions();
        RealMatrix tempData = null;
        RealMatrix tempSolutions = null;
        ArrayList<ArrayList<Double>> tempDynamicData = new ArrayList<>();
        ArrayList<ArrayList<Double>> tempDynamicSolutions = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] inputOutput = line.split(",");
                ArrayList<Double> tempRowData = new ArrayList();
                ArrayList<Double> tempRowSolution = new ArrayList();
                // Add x subscript 0 which will always be one.
                tempRowData.add(1d);

                for (int i = 0; i < inputOutput.length - 1; i++) {
                    tempRowData.add(Double.parseDouble(inputOutput[i]));
                }

                tempRowSolution.add(Double.parseDouble(inputOutput[inputOutput.length - 1]));
                tempDynamicData.add(tempRowData);
                tempDynamicSolutions.add(tempRowSolution);
            }

            tempData = new Array2DRowRealMatrix(tempDynamicData.size(), tempDynamicData.get(0).size());
            tempSolutions = new Array2DRowRealMatrix(tempDynamicSolutions.size(), 1);

            for (int i = 0; i < tempDynamicData.size(); i++) {
                for (int j= 0; j < tempDynamicData.get(i).size(); j++) {
                    tempData.setEntry(i, j, tempDynamicData.get(i).get(j));
                }
                tempSolutions.setEntry(i, 0, tempDynamicSolutions.get(i).get(0));
            }

            dataAndSolutions.setData(tempData);
            dataAndSolutions.setSolution(tempSolutions);
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("error reading line");
            e.printStackTrace();
        }

        return dataAndSolutions;
    }
}
