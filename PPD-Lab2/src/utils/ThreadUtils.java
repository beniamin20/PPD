package utils;

import model.Complex;
import utils_thread.ComplexSum;
import utils_thread.SumThread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Beni on 10/17/2017.
 */

public class ThreadUtils {

    static List<Integer> portions = new ArrayList<>();
    static List<Integer> multPortions = new ArrayList<>();

    public static Complex[][] addTwoMatrices(Complex[][] a, Complex[][] b, int nrOfThreads) {
        Complex[][] tempSumMatrix = new Complex[a.length][a[0].length];
        List<Pair> firstPairs = getPairs(a);
        List<Integer> positions = new ArrayList<>();
        positions.add(0);
        positions.addAll(getPositionsList(returnSplits(portions, firstPairs.size(), nrOfThreads)));
        for (int i = 0; i < positions.size() - 1; i++) {
            ComplexSum sumThread = new ComplexSum(a, b, tempSumMatrix, firstPairs.subList(positions.get(i), positions.get(i + 1)));
            sumThread.start();
            try {
                sumThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return tempSumMatrix;
    }

    private static List<Integer> normalizeList(List<Integer> splits) {
        Integer initialSize = splits.size() ^ 2;
        while (initialSize > 0) {
            Integer maxInt = Collections.max(splits);
            Integer minInt = Collections.min(splits);
            Integer maxPosition = splits.indexOf(maxInt);
            Integer minPosition = splits.indexOf(minInt);
            Integer sum = minInt + maxInt;
            if (sum % 2 == 0) {
                splits.set(maxPosition, sum / 2);
                splits.set(minPosition, sum / 2);
            } else {
                splits.set(maxPosition, (sum - 1) / 2);
                splits.set(minPosition, (sum + 1) / 2);
            }
            initialSize--;
        }
        return splits;
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

    private static List<Pair> getPairs(Complex[][] array) {
        List<Pair> pairs = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                pairs.add(new Pair(i, j));
            }
        }
        return pairs;
    }
}