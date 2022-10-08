package com.home.java.letcode.dp;

import java.util.*;

public class InvalidPara {
    public static void main(String[] args) {
        System.out.println(new InvalidPara().removeInvalidParentheses("())((((((((((b))("));

    }


    public List<String> removeInvalidParentheses(String s) {

        Character openBrace = '(';
        Character closeBrace = ')';
        char character[] = s.toCharArray();
        int openCount = 0;
        int closeCount = 0;
        List<String> output = new ArrayList<>();
        int missedOpenBraces = 0;
        int missedCloseBraces = 0;
        int maxClosedIndex = -1;
        int maxOpendIndex = -1;

        List<Integer> list = new ArrayList<>();
        for (int index = 0; index < character.length; index++) {
            char val = character[index];
            if (val == openBrace) {
                openCount++;
            } else if (val == closeBrace) {
                closeCount++;
                list.add(index);
            }
            if (closeCount > openCount) {
                missedCloseBraces++;
                maxClosedIndex = index;
                openCount = 0;
                closeCount = 0;

            }

        }
        openCount = 0;
        closeCount = 0;
        for (int index = character.length - 1; index >= 0; index--) {
            char val = character[index];
            if (val == openBrace) {
                openCount++;
            } else if (val == closeBrace) {
                closeCount++;
                list.add(index);
            }
            if (openCount > closeCount) {
                missedOpenBraces++;
                maxOpendIndex = index;
                openCount = 0;
                closeCount = 0;
            }

        }
        // valid case....
        if (maxClosedIndex == -1 && maxOpendIndex == -1) {
            output.add(s);
            return output;
        }

        String closedIndexString = maxClosedIndex != -1 ? s.substring(0, maxClosedIndex + 1) : null;
        String openedIndexString = maxOpendIndex != -1 ? s.substring(maxOpendIndex) : null;
        String commonString = "";
        if (maxOpendIndex != -1 && maxClosedIndex != -1) {
            commonString = s.substring(maxClosedIndex + 1, maxOpendIndex);
        } else if (maxOpendIndex != -1) {
            commonString = s.substring(0, maxOpendIndex);
        } else if (maxClosedIndex != -1) {
            commonString = s.substring(maxClosedIndex + 1);
        }

        Set<String> open = new HashSet<>();
        Set<String> close = new HashSet<>();
        List<String> previouslyProcessed = new ArrayList<>();
        if (closedIndexString != null)
            getAllPosibleValidString(closedIndexString, closeBrace, missedCloseBraces, close, previouslyProcessed);

        previouslyProcessed.clear();
        if (openedIndexString != null)
            getAllPosibleValidString(openedIndexString, openBrace, missedOpenBraces, open, previouslyProcessed);
        // invalid case big string

        if (open.size() == 0) {

            for (String suffix : close) {
                output.add(suffix + commonString);
            }

            if (output.size() == 0) {

                output.add(commonString);

            }
            return output;
        }

        if (close.size() == 0) {
            for (String suffix : open) {
                output.add(commonString + suffix);
            }

            if (output.size() == 0) {

                output.add(commonString);

            }
            return output;
        }
        for (String prefix : close) {
            for (String suffix : open) {
                output.add(prefix + commonString + suffix);
            }
        }


        if (output.size() == 0) {

            output.add(commonString);

        }

        return output;
    }

    public void getAllPosibleValidString(String s, char braces, int missedBraces, Set<String> set, List<String> previouslyProcessed) {
        if (missedBraces == 0) {
            boolean validString = checkValid(s.toCharArray());
            if (validString) {
                set.add(s);
            }
            return;
        }
        --missedBraces;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != braces)
                continue;
            String newString = s.substring(0, i) + s.substring(i + 1);
            if (previouslyProcessed.contains(newString))
                continue;
            previouslyProcessed.add(newString);
            getAllPosibleValidString(newString, braces, missedBraces, set, previouslyProcessed);
        }

    }


    public boolean checkValid(char[] character) {
        int openCount = 0;
        int closeCount = 0;
        char openBrace = '(';
        char closeBrace = ')';
        for (int index = 0; index < character.length; index++) {
            char val = character[index];
            if (val == openBrace) {
                openCount++;
            } else if (val == closeBrace) {
                closeCount++;
            }
            if (closeCount > openCount)
                return false;
        }
        return openCount == closeCount;
    }


}
