package com.aloknath.totango.Objects;

/**
 * Created by ALOKNATH on 3/9/2015.
 */
public class ContractValue {

    private double sum ;
    private double avg ;


    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public double getAvg() {
        return avg;
    }

    public void setAvg(double avg) {
        this.avg = avg;
    }

    @Override
    public String toString() {
        return "ContractValue{" +
                "sum=" + sum +
                ", avg=" + avg +
                '}';
    }
}
