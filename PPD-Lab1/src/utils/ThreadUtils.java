package utils;

import utils_thread.MultiplicationThread;
import utils_thread.SumThread;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Beni on 10/17/2017.
 */

public class ThreadUtils {

    static List<Integer> portions = new ArrayList<>();
    static List<Integer> multPortions = new ArrayList<>();

    public static int[][] addTwoMatrices(int[][] a, int[][] b, int nrOfThreads) {

        int[][] tempSumMatrix = new int[a.length][a[0].length];
        List<PairUtils> firstPairs = getPairs(a);
        List<Integer> positions = new ArrayList<>();

        positions.add(0);
        positions.addAll(getPositionsList(returnSplits(portions, firstPairs.size(), nrOfThreads)));
        for (int i = 0; i < positions.size() - 1; i++) {
            SumThread sumThread = new SumThread(a, b, tempSumMatrix, firstPairs.subList(positions.get(i), positions.get(i + 1)));
            sumThread.start();
            try {
                sumThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return tempSumMatrix;
    }

    public static int[][] multiplyTwoMatrices(int[][] a, int[][] b, int nrOfThreads) {

        int[][] tempMultMatrix = new int[a.length][b[0].length];
        List<PairUtils> firstPairs = getPairs(a);
        List<PairUtils> secondPairs = getPairs(b);
        List<PairUtils> finalPairs = new ArrayList<>();

        for (int i = 0; i < firstPairs.size(); i++) {
            for(int j = 0; j < secondPairs.size(); j++) {
                finalPairs.add(new PairUtils(firstPairs.get(i).getRow(), secondPairs.get(j).getColumn()));
            }
        }

        List<Integer> positions = new ArrayList<>();
        positions.add(0);
        positions.addAll(getPositionsList(returnSplits(multPortions, finalPairs.size(), nrOfThreads)));

        for (int i = 0; i < positions.size() - 1; i++) {
            MultiplicationThread multiplicationThread = new MultiplicationThread(finalPairs.subList(positions.get(i), positions.get(i + 1)), a, b, tempMultMatrix);
            multiplicationThread.start();
            try {
                multiplicationThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return tempMultMatrix;
    }

    private static List<Integer> getPositionsList(List<Integer> splits) {
        List<Integer> positions = new ArrayList<>();

        for (int i = 0; i < splits.size(); i++) {
            if (i == 0) {
                positions.add(0, splits.get(0));

            } else {
                positions.add(i, splits.get(i) + positions.get(i - 1));
            }
        }
        return positions;
    }

    private static List<Integer> returnSplits(List<Integer> portions, int number, int division) {
        int rest = number % (division);

        if (rest == 0) {
            for (int i = 0; i < division; i++) {
                portions.add(number / division);
            }

        } else {
            portions.add(rest);
            number = number - rest;
            division -= 1;
            returnSplits(portions, number, division);
        }
        return portions;
    }

    private static List<PairUtils> getPairs(int[][] array) {
        List<PairUtils> pairs = new ArrayList<>();

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                pairs.add(new PairUtils(i, j));
            }
        }
        return pairs;
    }
}

