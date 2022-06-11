package com.home.java.letcode.dp;

import java.util.ArrayList;
import java.util.List;

public class GrayCode {

    public static void main(String[] args) {
        int n = 3;
        System.out.println(new GrayCode().grayCode(n));


    }


    public List<Integer> grayCode(int n) {
        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(1);
        if (n == 1)
            return list;

        int maxValue = (int) Math.pow(2, n) - 1;
        int start = 1;
        List<Integer> newList = new ArrayList<>();
        while (maxValue > list.size()) {
            int listSize = list.size();
            int levelMaxValue = (int) Math.pow(2, start);
            newList.clear();
            for (int i = listSize - 1; i >= 0; i--) {
                int value = list.get(i);
                int updateValue = value | levelMaxValue;
                newList.add(updateValue);

            }
            list.addAll(newList);
            start++;
        }
        return list;
    }


    public int getValue(int[] value) {
        int sum = 0;
        int i = 0;
        for (int v : value) {
            sum += Math.pow(2, i) * v;
            i++;
        }
        return sum;
    }


}



