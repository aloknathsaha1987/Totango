package com.aloknath.totango.Objects;

/**
 * Created by ALOKNATH on 3/9/2015.
 */
public class Health {

    private int missing;
    private ColorObject red;
    private ColorObject green;
    private ColorObject yellow;

    public int getMissing() {
        return missing;
    }

    public void setMissing(int missing) {
        this.missing = missing;
    }

    public ColorObject getRed() {
        return red;
    }

    public void setRed(ColorObject red) {
        this.red = red;
    }

    public ColorObject getGreen() {
        return green;
    }

    public void setGreen(ColorObject green) {
        this.green = green;
    }

    public ColorObject getYellow() {
        return yellow;
    }

    public void setYellow(ColorObject yellow) {
        this.yellow = yellow;
    }

    @Override
    public String toString() {
        return "Health{" +
                "missing=" + missing +
                ", red=" + red +
                ", green=" + green +
                ", yellow=" + yellow +
                '}';
    }
}
