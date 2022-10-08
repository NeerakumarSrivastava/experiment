package com.home.java.letcode.dp;

public class NumberOfBST {

    public static void main(String[] args) {
        System.out.println(new NumberOfBST().numTrees(3));

    }

    public int numTrees(int n) {
        int binarytree[] = new int[n + 1];
        binarytree[0] = 1;
        binarytree[1] = 1;
        // Logic Root node binary tree count = left node binary tree count+ right node binary tree count

        for (int i = 2; i <= n; i++) {  // how many   node/number of digit..
            for (int j = 1; j <= i; j++) {   // which digit is root now..
                binarytree[i] += binarytree[j - 1] * binarytree[i - j];
            }

        }
        return binarytree[n];
    }


}
