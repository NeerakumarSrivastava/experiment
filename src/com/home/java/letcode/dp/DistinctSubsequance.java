package com.home.java.letcode.dp;

import java.util.*;

public class DistinctSubsequance {

    public static void main(String[] args) {
        //System.out.println(new DistinctSubsequance().numDistinct1("babgbag", "bag"));


        System.out.println(new DistinctSubsequance().numDistinct("abcc",
                "abcc"));


    }


    public int numDistinct1(String s, String t) {

        char main[] = s.toCharArray();
        char subarr[] = t.toCharArray();
        int column = main.length;
        int row = subarr.length;
        Map<Integer, List<Node>> map = new HashMap<>();
        Set<Character> setMain=new HashSet<>();
        Set<Character> setSub=new HashSet<>();
        for (int i = 0; i < row; i++) {
            char matchingChar = subarr[i];
            setSub.add(matchingChar);
            for (int j = 0; j < column; j++) {
                if (matchingChar == main[j]) {

                    setMain.add(matchingChar);

                    if (map.containsKey(i)) {

                        Node node = new Node(j, i == 0 ? 1 : 0);
                        map.get(i).add(node);
                    } else {
                        List<Node> list = new ArrayList();
                        Node node = new Node(j, i == 0 ? 1 : 0);
                        list.add(node);
                        map.put(i, list);
                    }
                }
            }

        }

        if (map.size() == 0) {
            return 0;
        }

        List<Node> listPrevious = map.get(0);
        if (listPrevious.size() == main.length) {
            if (listPrevious.size() == subarr.length&&map.size()==subarr.length)
                return 1;
            if (listPrevious.size() == subarr.length&&map.size()!=subarr.length)
                return 0;
        }

        if(setMain.size()==setSub.size()&&setMain.size()==1&&listPrevious.size()==map.size())
            return 1;



        for (int i = 1; i < map.keySet().size(); i++) {
            List<Node> listCurrent = map.get(i);
            for (Node prevValue : listPrevious) {
                for (Node currentValue : listCurrent) {
                    if (prevValue.index < currentValue.index) {
                        currentValue.pathcount += prevValue.pathcount;
                    }
                }
            }
            listPrevious = listCurrent;
        }
        int totalPaths = 0;


        for (Node node : map.get(map.size() - 1)) {
            totalPaths += node.pathcount;
        }
        return totalPaths;

    }

    static class Node {
        int index;

        public Node(int index, int pathcount) {
            this.index = index;
            this.pathcount = pathcount;
        }

        int pathcount;
    }


    public int numDistinct(String s, String t) {

        int m = t.length();
        int n = s.length();

        // T can't appear as a subsequence in S
        if (m > n)
            return 0;

        // mat[i][j] stores the count of
        // occurrences of T(1..i) in S(1..j).
        int mat[][] = new int[m + 1][n + 1];

        // Initializing first column with
        // all 0s. An emptystring can't have
        // another string as subsequence
        for (int i = 1; i <= m; i++)
            mat[i][0] = 0;

        // Initializing first row with all 1s.
        // An empty string is subsequence of all.
        for (int j = 0; j <= n; j++)
            mat[0][j] = 1;

        // Fill mat[][] in bottom up manner
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // If last characters don't match,
                // then value is same as the value
                // without last character in S.
                if (t.charAt(i - 1) != s.charAt(j - 1))
                    mat[i][j] = mat[i][j - 1];

                    // Else value is obtained considering two cases.
                    // a) All substrings without last character in S
                    // b) All substrings without last characters in
                    // both.
                else
                    mat[i][j] = mat[i][j - 1] + mat[i - 1][j - 1];
            }
        }

        /* uncomment this to print matrix mat
        for (int i = 1; i <= m; i++, cout << endl)
            for (int j = 1; j <= n; j++)
                System.out.println ( mat[i][j] +" "); */
        return mat[m][n];

    }


}
