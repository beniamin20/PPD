package utils_thread;

import utils.Pair;
import java.util.List;

/**
 * Created by Beni on 10/14/2017.
 */

public class MultiplicationThread extends Thread {
    private List<Pair> pairs;
    private int[][] firstMatrix;
    private int[][] secondMatrix;
    private int[][] multMatrix;

    public MultiplicationThread(List<Pair> pairs, int[][] firstMatrix, int[][] secondMatrix, int[][] multMatrix) {
        this.pairs = pairs;
        this.firstMatrix = firstMatrix;
        this.secondMatrix = secondMatrix;
        this.multMatrix = multMatrix;
    }

    //construct a pair array - combinations of first's numbers of rows and b's number of columns.

    @Override
    public void run() {
        for (Pair p : pairs) {
            int row = p.getRow();
            int column = p.getColumn();
            multMatrix[row][column] = calculateMultiplicationElement(row, column);
        }
    }

    private static int max(int a, int b) {
        return  a >=b ? a : b;
    }

    private int calculateMultiplicationElement(int row, int column) {
        int multiplication = 0;

        for (int i = 0; i < max(multMatrix[0].length, multMatrix.length); i++) {
            try {
                multiplication += firstMatrix[row][i] * secondMatrix[i][column];
            } catch (Exception ignored) {
            }
        }
        return multiplication;
    }
}

