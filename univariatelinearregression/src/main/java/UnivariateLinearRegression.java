import Util.TrainingDataReader;
import ml.GradientDescent;
import model.Hypothesis;
import model.TrainingData1;

import java.util.ArrayList;
import java.util.Scanner;

public class UnivariateLinearRegression {
    public static void main(String[] args) {
        Double learningRate = .00001;
        Hypothesis hypothesis = new Hypothesis();
        hypothesis.setTheta0(0.0);
        hypothesis.setTheta1(0.0);
        ArrayList trainingData;

        System.out.println("Please enter the filepath for your test data");
        Scanner kb = new Scanner(System.in);
        String filepath = kb.nextLine();
        TrainingDataReader trainingDataReader = new TrainingDataReader();
        trainingData = trainingDataReader.getTrainingData(filepath);

        GradientDescent gradientDescent = new GradientDescent();
        gradientDescent.calculateGradientDescent(learningRate, trainingData, hypothesis);

        System.out.println(hypothesis.toString());
    }
}
