package utils.operator;

import model.Complex;

public class CustomOperatorComplex extends Operator<Complex> {
    // 1/ (1/ a + 1/b)

    @Override
    public Complex apply(Complex first, Complex second) {
        return new Complex((first.getReal() + second.getReal()), (first.getImaginary() + second.getImaginary()));
    }
}