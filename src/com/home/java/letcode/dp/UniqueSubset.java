package com.home.java.letcode.dp;

import java.util.*;

public class UniqueSubset {
    public static void main(String[] args) {
        int nums[]={1,2,2};
        System.out.println(new UniqueSubset().subsets(nums));
    }

    public List<List<Integer>> subsets(int[] nums) {

        int numberOfLevels = nums.length;
        Map<Integer, List<List<Integer>>> map = new HashMap<>();
        List<List<Integer>> finalListOfList = new ArrayList<>();
        for (int i = 0; i < numberOfLevels; i++) {
            List<Integer> list = new ArrayList<>();
            list.add(nums[i]);
            List<List<Integer>> listOfList = new ArrayList<>();
            listOfList.add(list);
            if (!finalListOfList.contains(list)) {
                finalListOfList.addAll(listOfList);
            }
                map.put(i, listOfList);

        }
        for (int i = 1; i < numberOfLevels; i++) {
            int value = nums[i - 1];
            for (int j = i; j < numberOfLevels; j++) {
                List<List<Integer>> listofList = map.get(j);
                List<List<Integer>> newlistofList = new ArrayList<>();
                for (List<Integer> list : listofList) {
                    List<Integer> newlist = new ArrayList<>(list);
                    newlist.add(value);
                    Collections.sort(newlist);
                    if (!finalListOfList.contains(newlist)) {
                        finalListOfList.add(newlist);
                    }
                    newlistofList.add(newlist);
                }
                listofList.addAll(newlistofList);
            }
        }
        finalListOfList.add(new ArrayList());
        return finalListOfList;
    }

}
