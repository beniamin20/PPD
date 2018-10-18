package com.company.ppdlab1;

import model.Complex;
import utils.MatrixUtils;
import utils.Pair;
import utils.ThreadUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static utils.MatrixUtils.*;

public class Main {
    static List<Pair> pairs = new ArrayList<>();
    static List<Pair> secondPairs = new ArrayList<>();
    static int nrOfThreads;

    public static void main(String[] args) {
        nrOfThreads = readNrOfThreads();
        int nrOfRows = readNrOfRows();
        int nrOfColumns = readNrOfColumns();

        try {
            MatrixUtils.writeComplexFile(nrOfRows, nrOfColumns, "./complex.txt");
            Complex[][] stuff1 = MatrixUtils.readComplexFile("./complex.txt");
            MatrixUtils.writeComplexFile(nrOfRows, nrOfColumns, "./complex1.txt");
            Complex[][] stuff2 = MatrixUtils.readComplexFile("./complex1.txt");
            double initialTime = System.nanoTime();
            Complex[][] result = ThreadUtils.addTwoMatrices(stuff1, stuff2, 1);
            double finishTime = System.nanoTime();
            System.out.println("---------------------------------------------------");
            double time = (finishTime - initialTime) / 1000000000d;
            System.out.println("It took: " + time);
            printComplexArray(stuff1);
            System.out.println("-------------");
            printComplexArray(stuff2);
            System.out.println("-------------");
            printComplexArray(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printComplexArray(Complex[][] result) {
        for (Complex[] x : result) {
            for (Complex y : x) {
                System.out.print(y);
                System.out.print(" ");
            }
            System.out.println("");
        }
    }

    private static void printIntArray(int[][] second) {
        for (int i = 0; i < second.length; i++) {
            for (int j = 0; j < second[0].length; j++) {
                System.out.print(second[i][j] + " ");
            }
            System.out.println();
        }
    }
}

