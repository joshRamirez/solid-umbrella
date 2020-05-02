import ml.GradientDescent;
import model.Hypothesis;
import model.TrainingData1;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class GradientDescentTest {
    GradientDescent gradientDescent = new GradientDescent();
    ArrayList<TrainingData1> trainingData = new ArrayList();
    Hypothesis hypothesis = new Hypothesis();
    final Double delta = .005;
    final Double learningRate = .00001;

    @Before
    public void setUp() {
        hypothesis = new Hypothesis();
        hypothesis.setTheta0(0.0);
        hypothesis.setTheta1(2.0);
        trainingData = new ArrayList();
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
    }

    @Test
    public void testHypothesize() {
        hypothesis.setTheta0(1.0);
        Double input = 3.0;
        Double expectedResult = 7.0;

        assertEquals("Calculating hypothesis failed", expectedResult, gradientDescent.hypothesize(input, hypothesis));
    }

    @Test
    public void testGetCost() {
        Double expectedResult = 0.2;

        assertEquals("Getting the cost failed", expectedResult, gradientDescent.getCost(trainingData.get(0), hypothesis), delta);
    }

    @Test
    public void testGetSquaredErrorCost() {
        Double expectedResult = 0.008;

        assertEquals("Calculating squared error failed", expectedResult, gradientDescent.getSquaredErrorCost(trainingData, hypothesis), delta);
    }

    @Test
    public void testGetDerivativeWithRespectToTheta0() {
        Double expectedResult = 0.016;

        assertEquals("Getting derivative with respect to theta0 failed", expectedResult, gradientDescent.getDerivativeWithRespectToTheta0(trainingData, hypothesis), delta);
    }

    @Test
    public void testGetDerivativeWithRespectToTheta1() {
        Double expectedResult = 5.0;

        assertEquals("Getting derivative with respect to theta1 failed", expectedResult, gradientDescent.getDerivativeWithRespectToTheta1(trainingData, hypothesis), delta);
    }

    @Test
    public void testCalculateGradientDescent() {
        Double expectedResultTheta0 = -0.01;
        Double expectedResultTheta1 = 2.0;

        gradientDescent.calculateGradientDescent(learningRate, trainingData, hypothesis);
        assertEquals("Theta0 is not correct", expectedResultTheta0, hypothesis.getTheta0(), delta);
        assertEquals("Theta1 is not correct", expectedResultTheta1, hypothesis.getTheta1(), delta);
    }
}
