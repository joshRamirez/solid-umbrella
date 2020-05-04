package model;

import org.apache.commons.math3.linear.RealMatrix;

public class DataAndSolutions {
    private RealMatrix data;
    private RealMatrix solution;

    public RealMatrix getSolution() {
        return solution;
    }

    public void setSolution(RealMatrix solution) {
        this.solution = solution;
    }

    public RealMatrix getData() {
        return data;
    }

    public void setData(RealMatrix data) {
        this.data = data;
    }
}
