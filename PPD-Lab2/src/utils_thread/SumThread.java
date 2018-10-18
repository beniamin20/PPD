package utils_thread;

import utils.Pair;
import utils.operator.Operator;

import java.util.List;

public abstract class SumThread<T> extends Thread {
    private List<Pair> pairs;
    private T[][] firstMatrix;
    private T[][] secondMatrix;
    private T[][] sumMatrix;

    public SumThread(T[][] firstMatrix, T[][] secondMatrix, T[][] sumMatrix, List<Pair> pairs) {
        this.firstMatrix = firstMatrix;
        this.secondMatrix = secondMatrix;
        this.sumMatrix = sumMatrix;
        this.pairs = pairs;
    }

    @Override
    public void run() {
        for (Pair pair : pairs) {
            int row = pair.getRow();
            int column = pair.getColumn();
            try {
                sumMatrix[row][column] = getOperator().apply(firstMatrix[row][column], secondMatrix[row][column]);
            } catch (Exception e) {
                System.out.println("ROW: " + row + " COLUMN " + column);
            }
        }
    }

    public abstract Operator<T> getOperator();
}

