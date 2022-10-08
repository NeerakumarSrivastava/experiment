package com.home.java.letcode.dp;

public class NonNegativeIntegerWithoutConsequtiveOnes {

    public static void main(String[] args) {

        System.out.println(new NonNegativeIntegerWithoutConsequtiveOnes().findIntegers(100000000));
    }


    public int findIntegers(int n) {

        int output = 3;
        int count = 2;
        for (int i = 3; i <= n; i++) {
            int num = i;
            int number = (int) Math.pow(2, count);
            if (number < num) {
                count++;
            } else {
                number = (int) Math.pow(2, count - 1);
            }
            number = i ^ number;
            int j = i >> 1;
            number = number & j;
            if (number == 0) {
                output++;
            }
        }
        return output;
    }


}
