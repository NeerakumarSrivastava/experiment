package com.home.java.letcode.dp;

import java.util.*;

public class SubSets {

    public static void main(String[] args) {
        Integer a[] = {1, 2, 3, 4};
        System.out.println(new SubSets().subsets(a));

    }

    public List<List<Integer>> subsetsWithDup(Integer[] nums) {

        int numberOfLevels = nums.length;

        List<List<Integer>> finalListOfList = new ArrayList<>();
        Map<Integer, Set<List<Integer>>> partialListOfListMap = new HashMap<>();

        Set<List<Integer>> listOfList = new HashSet<>();
        for (int level = 0; level < numberOfLevels; level++) {
            List<Integer> listInit = new ArrayList();
            listInit.add(nums[level]);
            listOfList.add(listInit);
        }
        partialListOfListMap.put(0, listOfList);

        for (int i = 0; i < numberOfLevels; ) {
            Set<List<Integer>> listOfListPart = partialListOfListMap.get(i);
            Set<List<Integer>> listOfListPartB = new HashSet<>();
            for (List<Integer> integers : listOfListPart) {
                for (Integer v1 : nums) {
                    if (integers.contains(v1)) {
                        continue;
                    }
                    List<Integer> list1 = new ArrayList<>(integers);
                    list1.add(v1);
                    Collections.sort(list1);
                    listOfListPartB.add(list1);
                }

            }
            i++;
            partialListOfListMap.put(i, listOfListPartB);
        }


        for (int v : partialListOfListMap.keySet()) {
            finalListOfList.addAll(partialListOfListMap.get(v));
        }

        return finalListOfList;
    }


    public List<List<Integer>> subsets(Integer[] nums) {

        int numberOfLevels = nums.length;
        Map<Integer, List<List<Integer>>> map = new HashMap<>();
        List<List<Integer>> finalListOfList = new ArrayList<>();
        for (int i = 0; i < numberOfLevels; i++) {
            List<Integer> list = new ArrayList<>();
            list.add(nums[i]);
            List<List<Integer>> listOfList = new ArrayList<>();
            listOfList.add(list);
            finalListOfList.addAll(listOfList);
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
                    finalListOfList.add(newlist);
                    newlistofList.add(newlist);
                }
                listofList.addAll(newlistofList);
            }
        }
        finalListOfList.add(new ArrayList());
        return finalListOfList;
    }




}
