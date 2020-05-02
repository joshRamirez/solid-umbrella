package ml;

import model.Hypothesis;
import model.TrainingData1;

import java.util.ArrayList;

public class GradientDescent {

    public void calculateGradientDescent(Double learningRate, ArrayList<TrainingData1> trainingData1, Hypothesis hypothesis) {
        Double previousCost = getSquaredErrorCost(trainingData1, hypothesis) * 2;
        Double cost = getSquaredErrorCost(trainingData1, hypothesis);

        while (previousCost > cost) {
            takeAStep(learningRate, trainingData1, hypothesis);
            previousCost = cost;
            cost = getSquaredErrorCost(trainingData1, hypothesis);
        }
    }

    private void takeAStep(Double learningRate, ArrayList<TrainingData1> trainingData1, Hypothesis hypothesis) {
        Double tempTheta0 = hypothesis.getTheta0() - (learningRate * getDerivativeWithRespectToTheta0(trainingData1, hypothesis));
        Double tempTheta1 = hypothesis.getTheta1() - (learningRate * getDerivativeWithRespectToTheta1(trainingData1, hypothesis));

        hypothesis.setTheta0(tempTheta0);
        hypothesis.setTheta1(tempTheta1);
    }

    public Double getDerivativeWithRespectToTheta0(ArrayList<TrainingData1> trainingData1, Hypothesis hypothesis) {
        Double totalCost = 0.0;

        for (TrainingData1 pair : trainingData1) {
            Double cost = getCost(pair, hypothesis);
            totalCost += cost;
        }

        totalCost = totalCost / trainingData1.size();

        return totalCost;
    }

    public Double getDerivativeWithRespectToTheta1(ArrayList<TrainingData1> trainingData1, Hypothesis hypothesis) {
        Double totalCost = 0.0;

        for (TrainingData1 pair : trainingData1) {
            Double cost = getCost(pair, hypothesis) * pair.getInput();
            totalCost += cost;
        }

        totalCost = totalCost / trainingData1.size();

        return totalCost;
    }

    public Double getSquaredErrorCost(ArrayList<TrainingData1> trainingData1, Hypothesis hypothesis) {
        Double totalCost = 0.0;

        for (TrainingData1 pair : trainingData1) {
            Double cost = getCost(pair, hypothesis);
            cost *= cost;
            totalCost += cost;
        }

        totalCost = totalCost * .5 / trainingData1.size();

        return totalCost;
    }

    public Double getCost(TrainingData1 trainingData1, Hypothesis hypothesis) {
        Double output = hypothesize(trainingData1.getInput(), hypothesis);
        return output - trainingData1.getExpectedOutput();
    }

    public Double hypothesize(Double input, Hypothesis hypothesis) {
        return hypothesis.getTheta0() + (hypothesis.getTheta1() * input);
    }
}
