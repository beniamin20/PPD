package utils.operator;

import model.Complex;

public class MultOperatorComplex extends Operator<Complex> {
    @Override
    public Complex apply(Complex first, Complex second) {
        Double a1 = first.getReal();
        Double b1 = first.getImaginary();
        Double a2 = second.getReal();
        Double b2 = second.getImaginary();
        return new Complex((a1 * a2 - b1 * b2), (a1 * b2 + b1 * a2));
    }
}