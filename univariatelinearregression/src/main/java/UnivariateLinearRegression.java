import ml.GradientDescent;
import model.Hypothesis;
import model.TrainingData1;

import java.util.ArrayList;

public class UnivariateLinearRegression {
    public static void main(String[] args) {
        Hypothesis hypothesis = new Hypothesis();
        hypothesis.setTheta0(0.0);
        hypothesis.setTheta1(0.0);

        ArrayList trainingData = new ArrayList();
        TrainingData1 trainingData1 = new TrainingData1();
        trainingData1.setInput(1.1);
        trainingData1.setExpectedOutput(2.0);
        TrainingData1 trainingData2 = new TrainingData1();
        trainingData2.setInput(2.04);
        trainingData2.setExpectedOutput(4.0);
        TrainingData1 trainingData3 = new TrainingData1();
        trainingData3.setInput(906.0);
        trainingData3.setExpectedOutput(1812.0);
        TrainingData1 trainingData4 = new TrainingData1();
        trainingData4.setInput(-123.1);
        trainingData4.setExpectedOutput(-246.0);
        TrainingData1 trainingData5 = new TrainingData1();
        trainingData5.setInput(0.0);
        trainingData5.setExpectedOutput(0.0);
        trainingData.add(trainingData1);
        trainingData.add(trainingData2);
        trainingData.add(trainingData3);
        trainingData.add(trainingData4);
        trainingData.add(trainingData5);

        GradientDescent gradientDescent = new GradientDescent();
        gradientDescent.calculateGradientDescent(0.00001, trainingData, hypothesis);

        System.out.println(hypothesis.toString());
    }
}
