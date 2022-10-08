package com.home.java.letcode.dp;

import java.util.HashMap;
import java.util.Map;

public class NoTwoSameBallTogether {
    /*
     * Appraoch make fix last ball try until all are zero..
     * Using recursion... check all possible combination..
     *
     * */

    public static void main(String[] args) {

        String input = "rrbbbg";
        char arr[] = input.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for (Character i : arr) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i) + 1);
            } else {
                map.put(i, 1);
            }
        }
        int value = returnAllCombinationWithNoSameColorTogether(map.get('r') - 1, map.get('b'), map.get('g'), "red")
                + returnAllCombinationWithNoSameColorTogether(map.get('r'), map.get('b'), map.get('g') - 1, "green")
                + returnAllCombinationWithNoSameColorTogether(map.get('r'), map.get('b') - 1, map.get('g'), "blue");

        System.out.println(value);


    }


    public static int returnAllCombinationWithNoSameColorTogether(int r, int b, int g, String lastBall) {
        if (r == 0 && b == 0 && g == 0) {
            return 1;
        } else if ((r == 0 && b == 1 && g == 0) || (r == 1 && b == 0 && g == 0) || (r == 0 && b == 0 && g == 1)) {
            return 1;
        } else if ((r == 0 && b > 1 && g == 0) || (r > 1 && b == 0 && g == 0) || (r == 0 && b == 0 && g > 1)) {
            return 0;
        } else if (r < 0 || b < 0 || g < 0)
            return 0;


        if (lastBall.equals("red")) {
            return (returnAllCombinationWithNoSameColorTogether(r, b - 1, g, "blue") +
                    returnAllCombinationWithNoSameColorTogether(r, b, g - 1, "green"));

        } else if (lastBall.equals("blue")) {
            return (returnAllCombinationWithNoSameColorTogether(r - 1, b, g, "red")  +
                     returnAllCombinationWithNoSameColorTogether(r, b, g - 1, "green") );

        } else {
            return (returnAllCombinationWithNoSameColorTogether(r, b - 1, g, "blue") +
                    returnAllCombinationWithNoSameColorTogether(r - 1, b, g, "red"));
        }


    }


}
