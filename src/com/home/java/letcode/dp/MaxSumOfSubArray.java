package com.home.java.letcode.dp;

import javax.swing.plaf.IconUIResource;

public class MaxSumOfSubArray {

    public static void main(String[] args) {


    }

    public int getMaximumNumberOfSubArray(int[] nums) {
        int maxSum = 0;
        int currentSum = 0;
        boolean isAllNegative = true;
        int maxNum = Integer.MIN_VALUE;
        for (int v : nums) {
            currentSum += v;
            if (maxNum > v) {
                maxNum = v;
            }
            if (v >= 0) {
                isAllNegative = false;
            }
            if (currentSum < 0) {
                currentSum = 0;
                continue;
            }
            if (currentSum > maxSum) {
                maxSum = currentSum;
            }


        }
        if (isAllNegative) {
            return maxNum;
        }

        return maxSum;


    }


}
