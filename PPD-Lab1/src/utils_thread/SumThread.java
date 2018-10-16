package utils_thread;

import utils.PairUtils;

import java.util.List;
import java.util.stream.IntStream;

/**
 * Created by Beni on 10/17/2016.
 */

public class SumThread extends Thread {
    private List<PairUtils> pairs;
    private int[][] firstMatrix;
    private int[][] secondMatrix;
    private int[][] sumMatrix;

    public SumThread(int[][] firstMatrix, int[][] secondMatrix, int[][] sumMatrix, List<PairUtils> pairs) {
        this.firstMatrix = firstMatrix;
        this.secondMatrix = secondMatrix;
        this.sumMatrix = sumMatrix;
        this.pairs = pairs;
    }

    @Override
    public void run() {
        for (PairUtils pair : pairs) {
            int row = pair.getRow();
            int column = pair.getColumn();

            try {
                sumMatrix[row][column] = firstMatrix[row][column] + secondMatrix[row][column];

            } catch (Exception e) {
                System.out.println("Row - " + row + " Column - " + column);
            }
        }
    }
}

