package ml;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;

public class GradientDescent {
    public void calculateGradientDescent(double learningRate, RealMatrix trainingData, RealMatrix hypothesis, RealMatrix solutions) {
        double previousCost = getSquaredErrorCost(trainingData, hypothesis, solutions) * 2;
        double cost = getSquaredErrorCost(trainingData, hypothesis, solutions);

        while (previousCost > cost) {
            takeAStep(learningRate, trainingData, hypothesis, solutions);
            previousCost = cost;
            cost = getSquaredErrorCost(trainingData, hypothesis, solutions);
        }
    }

    private void takeAStep(double learningRate, RealMatrix trainingData, RealMatrix hypothesis, RealMatrix solutions) {
        RealMatrix tempHypothesis = new Array2DRowRealMatrix(hypothesis.getRowDimension(), 1);

        for (int i = 0; i < hypothesis.getRowDimension(); i++) {
            double theta = hypothesis.getEntry(i, 0) - (learningRate * getDerivativeWithRespectToTheta(trainingData, hypothesis, i, solutions));
            tempHypothesis.setEntry(i, 0, theta);
        }

        hypothesis.setColumnMatrix(0, tempHypothesis);
    }

    public double getDerivativeWithRespectToTheta(RealMatrix trainingData, RealMatrix hypothesis, int whichTheta, RealMatrix solutions) {
        double totalCost = 0.0;

        for (int i = 0; i < trainingData.getRowDimension(); i++) {
            double cost = getCost(trainingData.getRow(i), hypothesis, solutions.getEntry(i, 0)) * trainingData.getEntry(i, whichTheta);
            totalCost += cost;
        }

        totalCost = totalCost / trainingData.getRowDimension();

        return totalCost;
    }

    public double getSquaredErrorCost(RealMatrix trainingData, RealMatrix hypothesis, RealMatrix solutions) {
        double totalCost = 0.0;

        for (int i = 0; i < trainingData.getRowDimension(); i++) {
            double cost = getCost(trainingData.getRow(i), hypothesis, solutions.getEntry(i, 0));
            cost *= cost;
            totalCost += cost;
        }

        totalCost = totalCost * .5 / trainingData.getRowDimension();

        return totalCost;
    }

    public double getCost(double[] trainingData, RealMatrix hypothesis, double solution) {
        double output = hypothesize(trainingData, hypothesis);
        return output - solution;
    }

    public double hypothesize(double[] input, RealMatrix hypothesis) {
        double hypothesisResult = 0;

        for (int i = 0; i < input.length; i++) {
            hypothesisResult += (input[i] * hypothesis.getEntry(i, 0));
        }

        return hypothesisResult;
    }
}
