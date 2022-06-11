package com.home.java.letcode.dp;

import java.util.HashSet;
import java.util.Set;

public class HappyNumber {
    public static void main(String[] args) {
        int number = 1111111;
        System.out.println(new HappyNumber().isHappy(number));

    }

    public boolean isHappy(int number) {
        int rem = 0;
        int sum = 0;
        Set<Integer> set=new HashSet<>();

        while (true) {
            while (number != 0) {
                rem = number % 10;
                sum = sum + (rem * rem);
                number = number / 10;
            }

            if (sum == 1) {
                return true;
            } else if (set.contains(sum)) {
                return false;
            } else {
                set.add(sum);
                number = sum;
                sum = 0;
            }


        }


    }
}

