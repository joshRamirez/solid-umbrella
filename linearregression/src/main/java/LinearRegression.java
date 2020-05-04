import ml.GradientDescent;
import model.DataAndSolutions;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import util.DataReader;

import java.util.Scanner;

public class LinearRegression {
    public static void main(String[] args) {
        Double learningRate = .00001;
        RealMatrix hypothesis;
        RealMatrix trainingData;
        RealMatrix solutions;

        System.out.println("Please enter the filepath for your test data");
        Scanner kb = new Scanner(System.in);
        String filepath = kb.nextLine();
        DataReader dataReader = new DataReader();
        DataAndSolutions dataAndSolutions = dataReader.readData(filepath);
        trainingData = dataAndSolutions.getData();
        solutions = dataAndSolutions.getSolution();
        hypothesis = new Array2DRowRealMatrix(trainingData.getColumnDimension(), 1);

        for (int i = 0; i < trainingData.getColumnDimension(); i++) {
            hypothesis.setEntry(i, 0, 0d);
        }

        GradientDescent gradientDescent = new GradientDescent();
        gradientDescent.calculateGradientDescent(learningRate, trainingData, hypothesis, solutions);

        System.out.println(hypothesis.toString());
    }
}
