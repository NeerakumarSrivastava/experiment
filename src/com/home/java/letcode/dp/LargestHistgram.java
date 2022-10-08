package com.home.java.letcode.dp;

import javax.swing.plaf.IconUIResource;

public class LargestHistgram {
    public static void main(String[] args) {

        int[] nums = {2,1,4,5,1,3,3};
        System.out.println(new LargestHistgram().largestRectangleArea(nums));

    }

    public int largestRectangleArea(int[] heights) {

        int lastMin = -1;
        int currentMin=-1;
        int lastNumElement = -1;
        int previousNumber = -1;
        int finallastMin = -1;
        int finallastNumElement = -1;

        for (int value : heights) {

            if (value == 0) {
                finallastMin = finallastMin < lastMin ? lastMin : finallastMin;
                finallastNumElement = finallastNumElement < lastNumElement ? lastNumElement : finallastNumElement;
                lastMin = -1;
                lastNumElement = -1;
                previousNumber = -1;
                continue;

            }

            if (lastMin == -1) {
                lastMin = value;
                previousNumber = value;
                lastNumElement = 1;
                continue;
            }


            int minCase1 = Math.min(lastMin, value);
            int numElementCase1 = lastNumElement + 1;

            int minCase2 = Math.min(previousNumber, value);
            if (minCase2 * 2 >= minCase1 * numElementCase1) {
                lastMin = minCase2;
                lastNumElement = 2;
            } else if (minCase1 * numElementCase1 > value) {

                lastMin = lastMin * lastNumElement > minCase1 * numElementCase1 ? lastMin : minCase1;
                lastNumElement = lastMin * lastNumElement > minCase1 * numElementCase1 ? lastNumElement : numElementCase1;
            } else {
                lastMin = value;
                lastNumElement = 1;
            }
            previousNumber = value;


        }

        if (finallastMin == -1 && lastMin == -1)
            return 0;
        if (lastMin * lastNumElement > finallastMin * finallastNumElement) {

            finallastMin = lastMin;
            finallastNumElement = lastNumElement;
        }

        return finallastMin == -1 ? lastMin * lastNumElement : finallastMin * finallastNumElement;
    }
}
