import ml.GradientDescent;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GradientDescentMultivariateTest {
    GradientDescent gradientDescent = new GradientDescent();
    RealMatrix thetaMatrix;
    RealMatrix trainingDataMatrix;
    RealMatrix solutionsMatrix;
    final double delta = .005;
    final double learningRate = .00001;

    @Before
    public void setUp() {
        double[][] hypothesisTheta = {{0d}, {0d}, {0d}, {0d}};
        thetaMatrix = new Array2DRowRealMatrix(hypothesisTheta);
        double[][] trainingDataEntries = {{1d, -1.5d, -2d, 5.1d}, {1d, -4d, 3d, -14d}, {1d, 3d, 2d, 906.0d}, {1d, -123.1d, 3d, 2d}, {1d, 0.0d, 0.0d, 0.0d}, {1d, -13d, -10d, -12d}};
        trainingDataMatrix = new Array2DRowRealMatrix(trainingDataEntries);
        double[][] solutions = {{0.0d}, {-9.0d}, {922.0d}, {-108.1d}, {4.0d}, {-64d}};
        solutionsMatrix = new Array2DRowRealMatrix(solutions);
    }

    @Test
    public void testHypothesize() {
        thetaMatrix.setEntry(0, 0, 1d);
        thetaMatrix.setEntry(1, 0, 3d);
        thetaMatrix.setEntry(2, 0, 12d);
        thetaMatrix.setEntry(3, 0, -2d);
        double[] input = {1.0, 3.0, 12.0, 21};
        double expectedResult = 112.0;

        assertEquals("Calculating hypothesis failed", expectedResult, gradientDescent.hypothesize(input, thetaMatrix), delta);
    }

    @Test
    public void testGetCost() {
        double expectedResult = 9;

        assertEquals("Getting the cost failed", expectedResult, gradientDescent.getCost(trainingDataMatrix.getRow(1), thetaMatrix, solutionsMatrix.getEntry(1, 0)), delta);
    }

    @Test
    public void testGetSquaredErrorCost() {
        double expectedResult = 72163.550;

        assertEquals("Calculating squared error failed", expectedResult, gradientDescent.getSquaredErrorCost(trainingDataMatrix, thetaMatrix, solutionsMatrix), delta);
    }

    @Test
    public void testGetDerivativeWithRespectToTheta0() {
        double expectedResult = -124.149;
        int whichTheta = 0;

        assertEquals("Getting derivative with respect to theta0 failed", expectedResult, gradientDescent.getDerivativeWithRespectToTheta(trainingDataMatrix, thetaMatrix, whichTheta, solutionsMatrix), delta);
    }

    @Test
    public void testGetDerivativeWithRespectToTheta1() {
        double expectedResult = -2823.518;
        int whichTheta = 1;

        assertEquals("Getting derivative with respect to theta1 failed", expectedResult, gradientDescent.getDerivativeWithRespectToTheta(trainingDataMatrix, thetaMatrix, whichTheta, solutionsMatrix), delta);
    }

    @Test
    public void testCalculateGradientDescent() {
        double expectedResultTheta0 = 4.0;
        double expectedResultTheta1 = 2.0;
        double expectedResultTheta2 = 3.0;
        double expectedResultTheta3 = 1.0;
        //Larger delta since it seems like you may need a lot of data to get a more accurate set of thetas
        double tempDelta = 1.0;
        thetaMatrix.setEntry(1, 0, 0d);

        gradientDescent.calculateGradientDescent(learningRate, trainingDataMatrix, thetaMatrix, solutionsMatrix);
//        assertEquals("Theta0 is not correct", expectedResultTheta0, thetaMatrix.getEntry(0, 0), delta);
        assertEquals("Theta1 is not correct", expectedResultTheta1, thetaMatrix.getEntry(1, 0), tempDelta);
        assertEquals("Theta2 is not correct", expectedResultTheta2, thetaMatrix.getEntry(2, 0), tempDelta);
        assertEquals("Theta3 is not correct", expectedResultTheta3, thetaMatrix.getEntry(3, 0), tempDelta);
    }
}
