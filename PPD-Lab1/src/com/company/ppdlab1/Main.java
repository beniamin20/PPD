package com.company.ppdlab1;

import utils.PairUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static utils.MatrixUtils.*;
import static utils.ThreadUtils.addTwoMatrices;
import static utils.ThreadUtils.multiplyTwoMatrices;

public class Main {
    static List<PairUtils> pairs = new ArrayList<>();
    static List<PairUtils> secondPairs = new ArrayList<>();
    static int nrOfThreads;

    public static void main(String[] args) {
        nrOfThreads = readNrOfThreads();
        performSum();
        performMultiplication();
    }

    private static void performSum() {
        int nrOfRows = readNrOfRows();
        int nrOfColumns = readNrOfColumns();
        try {
            generateRandomArray(nrOfRows, nrOfColumns, "./firstMatrix.txt");
            generateRandomArray(nrOfRows, nrOfColumns, "./secondMatrix.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            int[][] first = readFile("./firstMatrix.txt");
            int[][] second = readFile("./secondMatrix.txt");

            System.out.println("-> First matrix:");
            printIntArray(first);
            System.out.println("-> Second matrix");
            printIntArray(second);

            double startTime = System.nanoTime();
            int[][] sum = addTwoMatrices(first, second, nrOfThreads);
            double endTime = System.nanoTime();
            double totalTime = (endTime - startTime) / 1000000000;

            System.out.println();
            System.out.println("-> Sum matrix:");
            printIntArray(sum);
            System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("Execution time: " + totalTime);
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\n");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void performMultiplication() {
        System.out.println("Multiplication:");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        int nrOfRows = readNrOfRows();
        int nrOfColumns = readNrOfColumns();
        printNrOfRows(nrOfColumns);
        int secondNrOfColumns = readNrOfColumns();

        try {
            generateRandomArray(nrOfRows, nrOfColumns, "./matrix1.txt");
            generateRandomArray(nrOfColumns, secondNrOfColumns, "./matrix2.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            int[][] first = readFile("./matrix1.txt");
            System.out.println("-> First matrix:");
//            printIntArray(first);

            System.out.println("-> Second matrix:");
            int[][] second = readFile("./matrix2.txt");
//            printIntArray(second);

            double startTime = System.nanoTime();
            int[][] mult = multiplyTwoMatrices(first, second, nrOfThreads);

            double endTime = System.nanoTime();
            double totalTime = (endTime - startTime) / 1000000000;
            System.out.println();
            System.out.println("-> Multiplyed matrix:");
//            printIntArray(mult);
            System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("Execution time: " + totalTime);
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
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

