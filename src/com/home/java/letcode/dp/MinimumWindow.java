package com.home.java.letcode.dp;

import java.util.*;

public class MinimumWindow {

    public static void main(String[] args) {
      //  String s = "acaabacababcbcacbbccbaccaaacacabbbcaccabccabbca", t = "bbaacbacbbccbcaabbacbacac";
        String s = "ADOBECODEBANC", t = "ABC";

        System.out.println(new MinimumWindow().minWindow(s, t));

    }

    public String minWindow(String s, String t) {
        HashMap<Character, Integer> goal = new HashMap<>();
        int goalSize = t.length();
        int minLen = Integer.MAX_VALUE;
        String result = "";

        //target dictionary
        for(int k=0; k<t.length(); k++){
            goal.put(t.charAt(k), goal.getOrDefault(t.charAt(k), 0)+1);
        }

        int i=0;
        int total=0;
        HashMap<Character, Integer> map = new HashMap<>();
        for(int j=0; j<s.length(); j++){
            char c = s.charAt(j);
            if(!goal.containsKey(c)){
                continue;
            }

            //if c is a target character in the goal, and count is < goal, increase the total
            int count = map.getOrDefault(c, 0);
            if(count<goal.get(c)){
                total++;
            }

            map.put(c, count+1);

            //when total reaches the goal, trim from left until no more chars can be trimmed.
            if(total==goalSize){
                while(!goal.containsKey(s.charAt(i)) || map.get(s.charAt(i))>goal.get(s.charAt(i))){
                    char pc = s.charAt(i);
                    if(goal.containsKey(pc) && map.get(pc)>goal.get(pc)){
                        map.put(pc, map.get(pc)-1);
                    }

                    i++;
                }

                if(minLen>j-i+1){
                    minLen = j-i+1;
                    result = s.substring(i, j+1);
                }
            }
        }

        return result;
    }



    /*
     * Approach find maxwindow
     * Remove one by one index.... from start and end....  and try to make short window...
     * return short window when more short is not possible...
     *
     * */

    public String minWindow1(String s, String t) {

        char sArr[] = s.toCharArray();
        char tArr[] = t.toCharArray();
        Map<Character, TreeSet<Integer>> main = new HashMap<>();
        Map<Character, Integer> sub = new HashMap<>();
        TreeSet<Integer> window = new TreeSet<>();

        for (int i = 0; i < tArr.length; i++) {
            char p = tArr[i];
            for (int j = 0; j < sArr.length; j++) {
                char m = sArr[j];
                if (p == m) {
                    if (main.containsKey(m)) {
                        main.get(m).add(j);
                    } else {
                        TreeSet<Integer> treeSet = new TreeSet<>();
                        treeSet.add(j);
                        main.put(m, treeSet);
                    }
                    window.add(j);
                }

            }
            if (sub.containsKey(p)) {
                sub.put(p, sub.get(p) + 1);
            } else {
                sub.put(p, 1);
            }
        }
        if (!validation(main, sub)) {
            return "";
        }
        return getMinString(window, main, sub, sArr, s);
    }


    public String getMinString(TreeSet<Integer> window, Map<Character, TreeSet<Integer>> main, Map<Character, Integer> sub, char[] sArr, String s) {
        while (true) {
            int first = window.first();
            int last = window.last();

            char firstValue = sArr[first];
            char lastValue = sArr[last];

            main.get(firstValue).remove(first);
            boolean validationF = validation(main, sub);
            main.get(firstValue).add(first);


            main.get(lastValue).remove(last);
            boolean validationL = validation(main, sub);
            main.get(lastValue).add(last);

            if (validationF && validationL) {
                Map<Character, TreeSet<Integer>> main1 = new HashMap<>();
                copiedHashMap(main, main1);
                TreeSet<Integer> window1 = new TreeSet<>(window);
                window1.remove(last);
                main1.get(lastValue).remove(last);
                String a = getMinString(window1, main1, sub, sArr, s);

                window.remove(first);
                main.get(firstValue).remove(first);


                String b = getMinString(window, main, sub, sArr, s);

                if (a.length() < b.length()) {
                    return a;
                }
                return b;
            } else if (validationF) {
                window.remove(first);
                main.get(firstValue).remove(first);
            } else if (validationL) {
                window.remove(last);
                main.get(lastValue).remove(last);

            } else {
                return s.substring(window.first(), window.last() + 1);


            }
        }


    }

    public void copiedHashMap(Map<Character, TreeSet<Integer>> main, Map<Character, TreeSet<Integer>> main1) {
        for (Character v : main.keySet()) {
            TreeSet<Integer> treeSet = new TreeSet<>(main.get(v));
            main1.put(v, treeSet);
        }
    }


    public boolean validation(Map<Character, TreeSet<Integer>> main, Map<Character, Integer> sub) {
        Set<Character> keySet = sub.keySet();

        if (main.keySet().size() == 0) {
            return false;
        }


        for (Character charV : keySet) {
            if (main.get(charV) == null || main.get(charV).size() < sub.get(charV)) {
                return false;
            }
        }
        return true;
    }
}
