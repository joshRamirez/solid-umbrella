package model;

public class Hypothesis {
    private Double theta0;
    private Double theta1;

    public Double getTheta0() {
        return theta0;
    }

    public void setTheta0(Double theta0) {
        this.theta0 = theta0;
    }

    public Double getTheta1() {
        return theta1;
    }

    public void setTheta1(Double theta1) {
        this.theta1 = theta1;
    }

    @Override
    public String toString() {
        return "Hypothesis{" +
                "theta0=" + theta0 +
                ", theta1=" + theta1 +
                "} \n" +
                "y = " + theta0 + " + " + theta1 + "x";
    }
}
