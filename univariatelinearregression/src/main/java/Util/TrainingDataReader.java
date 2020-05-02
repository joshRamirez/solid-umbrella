package Util;

import model.TrainingData1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TrainingDataReader {

    public ArrayList<TrainingData1> getTrainingData(String filepath) {
        ArrayList<TrainingData1> trainingData = new ArrayList<TrainingData1>();
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] inputOutput = line.split(",");
                trainingData.add(new TrainingData1(Double.parseDouble(inputOutput[0]), Double.parseDouble(inputOutput[1])));
            }
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("error reading line");
            e.printStackTrace();
        }

        return trainingData;
    }
}
