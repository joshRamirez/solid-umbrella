import ml.GradientDescent;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GradientDescentUnivariateTest {
    GradientDescent gradientDescent = new GradientDescent();
    RealMatrix thetaMatrix;
    RealMatrix trainingDataMatrix;
    RealMatrix solutionsMatrix;
    final double delta = .005;
    final double learningRate = .00001;

    @Before
    public void setUp() {
        double[][] hypothesisTheta = {{0d}, {2d}};
        thetaMatrix = new Array2DRowRealMatrix(hypothesisTheta);
        double[][] trainingDataEntries = {{1d, 1.1d}, {1d, 2.04d}, {1d, 906.0d}, {1d, -123.1d}, {1d, 0.0d}};
        trainingDataMatrix = new Array2DRowRealMatrix(trainingDataEntries);
        double[][] solutions = {{2.0d}, {4.0d}, {1812.0d}, {-246.0d}, {0.0d}};
        solutionsMatrix = new Array2DRowRealMatrix(solutions);
    }

    @Test
    public void testHypothesize() {
        thetaMatrix.setEntry(0, 0, 1d);
        double[] input = {1.0, 3.0};
        double expectedResult = 7.0;

        assertEquals("Calculating hypothesis failed", expectedResult, gradientDescent.hypothesize(input, thetaMatrix), delta);
    }

    @Test
    public void testGetCost() {
        double expectedResult = 0.2;

        assertEquals("Getting the cost failed", expectedResult, gradientDescent.getCost(trainingDataMatrix.getRow(0), thetaMatrix, solutionsMatrix.getEntry(0, 0)), delta);
    }

    @Test
    public void testGetSquaredErrorCost() {
        double expectedResult = 0.008;

        assertEquals("Calculating squared error failed", expectedResult, gradientDescent.getSquaredErrorCost(trainingDataMatrix, thetaMatrix, solutionsMatrix), delta);
    }

    @Test
    public void testGetDerivativeWithRespectToTheta0() {
        double expectedResult = 0.016;
        int whichTheta = 0;

        assertEquals("Getting derivative with respect to theta0 failed", expectedResult, gradientDescent.getDerivativeWithRespectToTheta(trainingDataMatrix, thetaMatrix, whichTheta, solutionsMatrix), delta);
    }

    @Test
    public void testGetDerivativeWithRespectToTheta1() {
        double expectedResult = 5.0;
        int whichTheta = 1;

        assertEquals("Getting derivative with respect to theta1 failed", expectedResult, gradientDescent.getDerivativeWithRespectToTheta(trainingDataMatrix, thetaMatrix, whichTheta, solutionsMatrix), delta);
    }

    @Test
    public void testCalculateGradientDescent() {
        double expectedResultTheta0 = -0.01;
        double expectedResultTheta1 = 2.0;
        thetaMatrix.setEntry(1, 0, 0d);

        gradientDescent.calculateGradientDescent(learningRate, trainingDataMatrix, thetaMatrix, solutionsMatrix);
        assertEquals("Theta0 is not correct", expectedResultTheta0, thetaMatrix.getEntry(0, 0), delta);
        assertEquals("Theta1 is not correct", expectedResultTheta1, thetaMatrix.getEntry(1, 0), delta);
    }
}
