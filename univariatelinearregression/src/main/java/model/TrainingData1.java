package model;

public class TrainingData1 {
    private Double input;
    private Double expectedOutput;

    public TrainingData1() {
    }

    public TrainingData1(Double input, Double expectedOutput) {
        this.input = input;
        this.expectedOutput = expectedOutput;
    }

    public Double getInput() {
        return input;
    }

    public void setInput(Double input) {
        this.input = input;
    }

    public Double getExpectedOutput() {
        return expectedOutput;
    }

    public void setExpectedOutput(Double expectedOutput) {
        this.expectedOutput = expectedOutput;
    }
}
