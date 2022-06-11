package com.home.java.letcode.dp;


import java.util.*;

public class MaximumRectangle {
    public static void main(String[] args) {

        char matrix[][] = {{'1', '0', '1', '1', '0', '1'},
                {'1', '1', '1', '1', '1', '1'},
                {'0', '1', '1', '0', '1', '1'},
                {'1', '1', '1', '0', '1', '0'},
                {'0', '1', '1', '1', '1', '1'},
                {'1', '1', '0', '1', '1', '1'}};
        new MaximumRectangle().maximalRectangle(matrix);
    }

    public int maximalRectangle(char[][] matrix) {
        int row = matrix.length;
        int column = matrix[0].length;
        Node nodes[][] = new Node[row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (((j - 1) >= 0) && ((i - 1) >= 0)) {
                    if ((matrix[i][j - 1] == '1') && (matrix[i][j] == '1') && (matrix[i - 1][j] == '1')) {
                        // check for previous island id..
                        Node nodeleft = nodes[i][j - 1];
                        Node nodeup = nodes[i - 1][j];
                        Node currenetNode = new Node();
                        currenetNode.maximumRectangleList.addAll(getCorelation(nodeup.maximumRectangleList, nodeleft.maximumRectangleList, i, j, matrix, row, column));

                        nodes[i][j] = currenetNode;

                    } else if ((matrix[i][j - 1] == '1') && (matrix[i][j] == '1')) {

                        Node nodeleft = nodes[i][j - 1];
                        Node currenetNode = new Node();

                        for (MaxRectangle maxRectangle : nodeleft.maximumRectangleList) {
                            MaxRectangle maxRectangle1 = new MaxRectangle(i, maxRectangle.startj
                                    , i, j);
                            currenetNode.addMaxRectangle(maxRectangle1);
                        }
                        nodes[i][j] = currenetNode;


                    } else if ((matrix[i][j] == '1') && (matrix[i - 1][j] == '1')) {
                        Node nodeup = nodes[i - 1][j];
                        Node currenetNode = new Node();

                        for (MaxRectangle maxRectangle : nodeup.maximumRectangleList) {
                            MaxRectangle maxRectangle1 = new MaxRectangle(maxRectangle.starti, j
                                    , i, j);
                            currenetNode.addMaxRectangle(maxRectangle1);
                        }

                        if (j + 1 < column) {
                            if (matrix[i - 1][j + 1] == '0') {
                                MaxRectangle maxRectangle = new MaxRectangle(i, j, i, j);
                                currenetNode.addMaxRectangle(maxRectangle);
                            }
                        }
                        nodes[i][j] = currenetNode;

                    } else if (matrix[i][j] == '1') {
                        // create new island id ...
                        Node node1 = new Node();
                        MaxRectangle maxRectangle1 = new MaxRectangle(i, j, i, j);
                        node1.addMaxRectangle(maxRectangle1);
                        nodes[i][j] = node1;
                    }
                } else if ((j - 1) >= 0) {
                    if ((matrix[i][j - 1] == '1') && (matrix[i][j] == '1')) {

                        Node nodeleft = nodes[i][j - 1];
                        Node currenetNode = new Node();

                        for (MaxRectangle maxRectangle : nodeleft.maximumRectangleList) {
                            MaxRectangle maxRectangle1 = new MaxRectangle(i, maxRectangle.startj
                                    , i, j);
                            currenetNode.addMaxRectangle(maxRectangle1);
                        }
                        nodes[i][j] = currenetNode;

                    } else if (matrix[i][j] == '1') {
                        // create new island id ...
                        Node node1 = new Node();
                        MaxRectangle maxRectangle1 = new MaxRectangle(i, j, i, j);
                        node1.addMaxRectangle(maxRectangle1);
                        nodes[i][j] = node1;

                    }


                } else if ((i - 1) >= 0) {
                    if ((matrix[i - 1][j] == '1') && (matrix[i][j] == '1')) {

                        Node nodeup = nodes[i - 1][j];
                        Node currenetNode = new Node();

                        for (MaxRectangle maxRectangle : nodeup.maximumRectangleList) {
                            MaxRectangle maxRectangle1 = new MaxRectangle(maxRectangle.starti, j
                                    , i, j);
                            currenetNode.addMaxRectangle(maxRectangle1);
                        }
                        if (j + 1 < column) {
                            if (matrix[i - 1][j + 1] == '0') {
                                MaxRectangle maxRectangle = new MaxRectangle(i, j, i, j);
                                currenetNode.addMaxRectangle(maxRectangle);
                            }
                        }
                        nodes[i][j] = currenetNode;
                    } else if (matrix[i][j] == '1') {
                        // create new island id ...
                        Node node1 = new Node();
                        MaxRectangle maxRectangle1 = new MaxRectangle(i, j, i, j);
                        node1.addMaxRectangle(maxRectangle1);
                        nodes[i][j] = node1;
                    }


                } else if (matrix[i][j] == '1') {
                    // create new island id ...
                    Node node1 = new Node();
                    MaxRectangle maxRectangle1 = new MaxRectangle(i, j, i, j);
                    node1.addMaxRectangle(maxRectangle1);
                    nodes[i][j] = node1;
                }
            }
        }
        int maxArea = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                Node node = nodes[i][j];
                if (node != null) {
                    for (MaxRectangle maxRectangle : node.maximumRectangleList) {
                        int area = (maxRectangle.endi - maxRectangle.starti + 1) * (maxRectangle.endj - maxRectangle.startj + 1);
                        if (area > maxArea) {
                            maxArea = area;
                        }

                    }

                }


            }

        }


        return maxArea;
    }

    public Set<MaxRectangle> getCorelation(Set<MaxRectangle> upNodeList, Set<MaxRectangle> leftNodeList, int i, int j, char[][] matrix, int row, int column) {

        Set<MaxRectangle> list = new HashSet<>();
        for (MaxRectangle upNode : upNodeList) {

            for (MaxRectangle leftNode : leftNodeList) {

                if (upNode.starti == leftNode.starti && upNode.startj == leftNode.startj) {
                    MaxRectangle maxRectangle = new MaxRectangle(upNode.starti, upNode.startj, i, j);
                    list.add(maxRectangle);
                }
                else  if ((upNode.startj != j) && (upNode.starti != i) && (leftNode.starti != i) && (leftNode.startj != j)) {

                    MaxRectangle maxRectangle = new MaxRectangle(leftNode.starti > upNode.starti ? leftNode.starti : upNode.starti
                            , leftNode.startj > upNode.startj ? leftNode.startj : upNode.startj, i, j);
                        list.add(maxRectangle);
                }
                else {
                    MaxRectangle maxRectangle = null;
                    if (leftNode.starti == i) {
                        maxRectangle = new MaxRectangle(leftNode.starti, leftNode.startj, i, j);
                        if (!list.contains(maxRectangle))
                            list.add(maxRectangle);
                    }
                    if (upNode.startj == j) {
                        maxRectangle = new MaxRectangle(upNode.starti, upNode.startj, i, j);
                        if (!list.contains(maxRectangle))
                            list.add(maxRectangle);
                    }


                    if ((i + 1) < row && (j + 1) < column && matrix[i + 1][j + 1] == '1'
                            && matrix[i + 1][j] == '1' && matrix[i][j + 1] == '1') {
                        maxRectangle = new MaxRectangle(i, j, i, j);
                        if (!list.contains(maxRectangle))
                            list.add(maxRectangle);
                    }
                }


            }
        }
        return list;
    }


    static class Node {
        int nodeId;
        private static int totalnodeId = 0;
        private Set<MaxRectangle> maximumRectangleList = new HashSet<>();

        Node() {
            totalnodeId++;
            this.nodeId = totalnodeId;
        }

        public boolean addMaxRectangle(MaxRectangle maxRectangle) {
            return maximumRectangleList.add(maxRectangle);
        }


    }

    static class MaxRectangle {


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            MaxRectangle that = (MaxRectangle) o;
            return starti == that.starti &&
                    startj == that.startj &&
                    endi == that.endi &&
                    endj == that.endj;
        }

        @Override
        public int hashCode() {
            return Objects.hash(starti, startj, endi, endj);
        }

        int starti;
        int startj;

        int endi;
        int endj;

        MaxRectangle(int starti, int startj, int endi, int endj) {
            this.starti = starti;
            this.endi = endi;
            this.startj = startj;
            this.endj = endj;
        }
    }


    public static int maximalRectangle1(char[][] matrix) {
        int row = matrix.length;
        int colunm = matrix[0].length;
        Island islandMatrix[][] = new Island[row][colunm];

        // check up and previous value...
        // Store the island in hashmap  .....
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < colunm; j++) {

                if ((j - 1) >= 0) {
                    if ((matrix[i][j - 1] == '1') && (matrix[i][j] == '1')) {
                        // check for previous island id..
                        Island islands = islandMatrix[i][j - 1];
                        if (islandMatrix[i][j] != null)
                            islandMatrix[i][j].setIslandId(islands.islandId);
                        else {
                            islandMatrix[i][j] = islandMatrix[i][j - 1];
                        }

                    } else if (matrix[i][j] == '1') {
                        // create new island id ...
                        Island island = new Island();
                        islandMatrix[i][j] = island;

                    }
                } else if (matrix[i][j] == '1') {
                    // create new island id ...
                    Island island = new Island();
                    islandMatrix[i][j] = island;

                }
                if ((i - 1) >= 0) {

                    if ((matrix[i - 1][j] == '1') && (matrix[i][j] == '1')) {

                        Island islands = islandMatrix[i - 1][j];
                        if (islandMatrix[i][j].islandId > islands.islandId) {
                            islandMatrix[i][j].setIslandId(islands.islandId);
                        } else {
                            islandMatrix[i - 1][j].setIslandId(islandMatrix[i][j].islandId);
                        }
                    }

                }


            }
        }

        return 0;
    }

    static class Island {
        private static int totalIsland = 0;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Island island = (Island) o;
            return islandId == island.islandId;
        }

        @Override
        public int hashCode() {
            return Objects.hash(islandId);
        }

        public void setIslandId(int islandId) {
            this.islandId = islandId;
        }

        private int islandId = 0;

        Island() {
            islandId = totalIsland++;
        }

    }

}
