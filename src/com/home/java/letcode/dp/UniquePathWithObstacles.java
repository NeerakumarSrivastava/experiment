package com.home.java.letcode.dp;

public class UniquePathWithObstacles {


    public static void main(String[] args) {


        new UniquePathWithObstacles().uniquePathsWithObstacles(null);
    }


    public int uniquePathsWithObstacles(int[][] obstacleGrid) {

        int numrow = obstacleGrid.length;
        int numcolumn = obstacleGrid[0].length;
        int[][] temp=new int[numrow][numcolumn];
        // 0 for path 1 for obstacles....
        for (int i = numrow - 1; i >= 0; i--) {
            for (int j = numcolumn - 1; j >= 0; j--) {
                if (obstacleGrid[i][j] == 0) {
                    if ((i + 1) < numrow && (j + 1) < numcolumn) {
                        temp[i][j] = temp[i + 1][j] + temp[i][j + 1];
                    } else if ((j + 1) < numcolumn) {
                        temp[i][j] = temp[i][j + 1];
                    } else if ((i + 1) < numrow) {
                        temp[i][j] = temp[i + 1][j];
                    } else {
                        temp[i][j] = 1;
                    }
                }

            }
        }
        return temp[0][0];
    }


}
