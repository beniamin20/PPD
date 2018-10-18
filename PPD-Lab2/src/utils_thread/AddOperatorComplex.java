package utils_thread;

import model.Complex;
import utils.operator.Operator;

public class AddOperatorComplex extends Operator<Complex> {
    @Override
    public Complex apply(Complex first, Complex second) {
        return new Complex((first.getReal() + second.getReal()), (first.getImaginary() + second.getImaginary()));
    }
}