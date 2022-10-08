package com.home.java.letcode.dp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ReversDiagnolPrint {
    public static void main(String[] args) {
        int arr[][] = {{1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16},};
        printReverseDiagonal2(arr);

    }

    public static void printReverseDiagonal(int[][] matrix) {

        int n = matrix.length;

        int numberOfIteration = 2 * n - 1;

        int i = 0;
        int j = n - 1;
        List<String> list = new ArrayList();
        Set<String> updatedlist = new HashSet<>();
        while (numberOfIteration > 0) {
            if (list.size() == 0) {
                System.out.println(matrix[i][j]);
                list.add(String.format("%d:%d", i, j));
                continue;
            } else {
                for (String val : list) {
                    String arr[] = val.split(":");
                    int rowV = Integer.parseInt(arr[0]);
                    int colV = Integer.parseInt(arr[1]);


                    if (rowV + 1 < n) {
                        updatedlist.add(String.format("%d:%d", rowV + 1, colV));
                    }

                    if (colV - 1 >= 0) {
                        updatedlist.add(String.format("%d:%d", rowV, colV - 1));
                    }
                }
            }


            list.clear();
            list.addAll(updatedlist);
            updatedlist.clear();

            for (String val : list) {
                String arr[] = val.split(":");
                int rowV = Integer.parseInt(arr[0]);
                int colV = Integer.parseInt(arr[1]);
                System.out.println(matrix[rowV][colV]);
            }
            System.out.println("........");


            numberOfIteration--;
        }


    }


    public static void printReverseDiagonal2(int[][] matrix) {

        int n = matrix.length;
        int numberOfIteration = 2 * n - 1;
        int offset = 1;
        int row = 0;
        int col = n - 1;

        while (numberOfIteration > 0) {
            int i = row;
            int j = col;
            int lasti = row;
            int lastj = col;
            while (i < n && j < n && i >= 0 && j >= 0) {
                System.out.println(matrix[i][j]);
                lasti = i;
                lastj = j;
                i += offset;
                j += offset;
            }
            if (offset > 0) {
                offset = -1;
            } else {
                offset = 1;
            }

            System.out.println("..........................");
            if (offset == 1) {
                if (lastj > 0) {
                    col = lastj - 1;
                    row=lasti;
                }
                else {
                    row =lasti+ 1;
                    col=lastj;
                }
            } else {
                if (lasti < n-1) {
                    row = lasti + 1;
                    col=lastj;
                } else {
                    row=lasti;
                    col = lastj - 1;
                }

            }
            numberOfIteration--;
        }

    }


}
