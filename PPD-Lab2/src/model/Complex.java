package model;

import java.text.DecimalFormat;

public class Complex {
    private Double real;
    private Double imaginary;
    private DecimalFormat df = new DecimalFormat("#.00");

    public Complex() {
    }

    public Complex(Double real, Double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public Double getReal() {
        return real;
    }

    public void setReal(Double real) {
        this.real = real;
    }

    public Double getImaginary() {
        return imaginary;
    }

    public void setImaginary(Double imaginary) {
        this.imaginary = imaginary;
    }

    @Override
    public String toString() {
        if (imaginary >= 0) {
            return df.format(real) + "+"
                    + df.format(imaginary) + "i";
        }
        return df.format(real) + ""
                + df.format(imaginary) + "i";
    }
}
