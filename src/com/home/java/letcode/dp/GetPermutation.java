package com.home.java.letcode.dp;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class GetPermutation {

    public static void main(String[] args) {
        System.out.println(new GetPermutation().getPermutation(0,1));

    }


    public String getPermutation(int n, int k) {
        int count = 1;
        List<Integer> treeset = new ArrayList<>();
        while (count <= n) {
            treeset.add(count);
            count++;
        }
        return calculateValue(treeset, "", k, n);
    }

    public static String calculateValue(List<Integer> integerSet, String prefixNumber, int k, int n) {

        if (k == 1) {
            return prefixNumber + getReverseNumber(integerSet);
        }

        int eachPermCount = getPermCount(n - 1);
        int digit = (int) Math.ceil(((double) k) / eachPermCount) - 1;
        int remain = k % eachPermCount;

        prefixNumber += "" + integerSet.get(digit);
        integerSet.remove(digit);
        return calculateValue(integerSet, prefixNumber, remain == 0 ? eachPermCount : remain, n - 1);

    }

    public static int getPermCount(int n) {
        int value = 1;
        while (n > 0) {
            value *= n;
            n--;
        }
        return value;
    }

    public static String getReverseNumber(List<Integer> n) {
        String value = "";
        for (int i = 0; i < n.size(); i++) {
            value += n.get(i);
        }
        return value;
    }

}
