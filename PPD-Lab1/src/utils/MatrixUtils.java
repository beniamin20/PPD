package utils;

import java.io.*;
import java.util.Scanner;


/**
 * Created by Beni on 10/17/2017.
 */

public class MatrixUtils {

    static Scanner scanner = new Scanner(System.in);

    public static int readNrOfThreads() {
        System.out.print("Insert number of threads:");
        return scanner.nextInt();
    }

    public static int readNrOfRows() {
        System.out.print("Insert nr of rows: ");
        return scanner.nextInt();
    }

    public static int printNrOfRows(int numberOfRows) {
        System.out.println(String.format("Number of rows in the second matrix: %d", numberOfRows));
        return numberOfRows;
    }

    public static int readNrOfColumns() {
        System.out.print("Insert nr of columns: ");
        return scanner.nextInt();
    }

    public static int[][] generateRandomArray(int rows, int columns, String filePath) throws IOException {
        int[][] temp = new int[rows][columns];
        BufferedWriter output = new BufferedWriter(new FileWriter(filePath));
        output.write(Integer.toString(rows));
        output.newLine();
        output.write(Integer.toString(columns));
        output.newLine();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                temp[i][j] = (int) (Math.random() * 10);
                output.write(Integer.toString(temp[i][j]) + " ");
            }
            output.newLine();
        }
        output.close();
        return temp;
    }

    public static int[][] readFile(String filePath) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filePath));
        int rows = scanner.nextInt();
        int columns = scanner.nextInt();
        int[][] temp = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                temp[i][j] = scanner.nextInt();
            }
        }
        return temp;
    }

}

