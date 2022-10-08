package com.home.java.letcode.dp;

import java.util.HashMap;
import java.util.Map;

public class FirstMissingPositive {
    public static void main(String[] args) {


    }

    public int firstMissingPositive(int[] nums) {
        // Maintaing hashmap  which has informatin related to previous digit..
        // Iterate hashmap and find key which hasnt previous digit...
        // find smallest from those missing previous digit...
        Map<Integer, Integer> nextDigitMap = new HashMap<>();
        int minimum = Integer.MIN_VALUE;
        boolean foundOne = false;
        for (int digit : nums) {
            if (digit > 0) {
                if (nextDigitMap.containsKey(digit - 1)) {
                    nextDigitMap.put(digit-1, digit);
                }

                if (nextDigitMap.containsKey(digit + 1)) {
                    nextDigitMap.put(digit, digit + 1);
                } else {
                    nextDigitMap.put(digit, null);
                }
                if (minimum < digit) {
                    minimum = digit;
                }
                if (digit == 1) {
                    foundOne = true;
                }
            }
        }
        if (!foundOne)
            return 1;


          //  minimum += 1;
        for (int digit : nextDigitMap.keySet()) {
            if (nextDigitMap.get(digit) == null) {

                if (minimum > digit) {
                    minimum = digit;
                }

            }
        }
        return minimum+1;


    }

}
