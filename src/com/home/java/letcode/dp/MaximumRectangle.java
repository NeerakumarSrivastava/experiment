package com.home.java.letcode.dp;


import java.util.*;

public class MaximumRectangle {
    public static void main(String[] args) {
        char matrix[][] = {{'0','0','1'},{'1','1','1'}};

        int arr[] = {4, 3, 2, 1, 5, 2, 9, 7};
        System.out.println(new MaximumRectangle().maximalRectangle(matrix));

    }

    public int maximalRectangle(char[][] matrix) {
        int row = matrix.length;
        int column = matrix[0].length;
        int maxArea = -1;
        int histgram[][] = new int[row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (i == 0) {
                    histgram[i][j] = matrix[i][j] - '0';
                    continue;
                }
                histgram[i][j] = (matrix[i][j] - '0') == 1 ? (matrix[i][j] - '0') + histgram[i - 1][j] : 0;
            }

        }
        for (int i = 0; i < row; i++) {
            int area = getMaxAreaOfRow(histgram[i]);
            if (area > maxArea) {
                maxArea = area;
            }
        }
        return maxArea;
    }

    public int getMaxAreaOfRow(int[] row) {
        int firstLeftSmallest[] = getSmallestElementArray(row);
        int firstRightSmallest[] = getSmallestElementArrayFromRight(row);
        int maxAreaOfRow = -1;
        for (int i = 0; i < row.length; i++) {
            int area = (firstRightSmallest[i] - firstLeftSmallest[i] - 1) * row[i];

            if (area > maxAreaOfRow)
                maxAreaOfRow = area;
        }
        return maxAreaOfRow;
    }

    public int[] getSmallestElementArray(int[] row) {
        int smallest[] = new int[row.length];
        Node stack[] = new Node[row.length];

        int top = -1;
        for (int i = 0; i < row.length; i++) {
            int element = row[i];

            if (top == -1) {
                top++;
                stack[top] = new Node(element, i);
                smallest[i] = -1;
                continue;
            }
            while (stack[top].value >= element) {
                top--;
                if (top == -1) {
                    break;
                }
            }
            top++;
            stack[top] = new Node(element, i);
            smallest[i] = top > 0 ? stack[top-1].index : -1;
        }
        return smallest;

    }

    public int[] getSmallestElementArrayFromRight(int[] row) {
        int smallest[] = new int[row.length];
        Node stack[] = new Node[row.length];
        int top = -1;
        for (int i = row.length - 1; i >= 0; i--) {
            int element = row[i];
            if (top == -1) {
                top++;
                stack[top] = new Node(element,i);
                smallest[i] = row.length;
                continue;
            }
            while (stack[top].value >= element) {
                top--;

                if (top == -1) {
                    break;
                }
            }
            top++;
            stack[top] = new Node(element,i);
            smallest[i] = top > 0 ? stack[top-1].index : row.length;;
        }
        return smallest;

    }

    static class Node {
        Node(int value, int index) {
            this.value = value;
            this.index = index;
        }

        private int value;
        private int index;

    }


}
