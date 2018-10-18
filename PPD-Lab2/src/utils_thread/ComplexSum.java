package utils_thread;

import model.Complex;
import utils.Pair;
import utils.operator.Operator;

import java.util.List;

public class ComplexSum extends SumThread<Complex> {
    public ComplexSum(Complex[][] firstMatrix, Complex[][] secondMatrix, Complex[][] sumMatrix, List<Pair> pairs) {
        super(firstMatrix, secondMatrix, sumMatrix, pairs);
    }

    @Override
    public Operator<Complex> getOperator() {
        return new AddOperatorComplex();
    }
}
