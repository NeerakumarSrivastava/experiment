package com.home.java.letcode.dp;

public class ValidNumber {
    public static void main(String[] args) {
        System.out.println(new ValidNumber().isNumber("5-e95"));

    }

    public int containChar(String s, String ch) {
        int i = -1;
        ch = ".";
        if ((i = s.indexOf(ch, 0)) != -1) {
            i = s.indexOf(ch, i + 1);
            if (i != -1)
                return 2;

        }
        ch = "e";
        if ((i = s.indexOf(ch, 0)) != -1) {
            i = s.indexOf(ch, i + 1);
            if (i != -1)
                return 2;
        }
        ch = "E";
        if ((i = s.indexOf(ch, 0)) != -1) {
            i = s.indexOf(ch, i + 1);
            if (i != -1)
                return 2;
            return 1;
        }

        return 0;
    }


    public boolean isNumber(String s) {

        if ((s.lastIndexOf('+') == (s.length() - 1)) || (s.lastIndexOf('-') == (s.length() - 1))) {
            return false;
        }
        if (s.contains("e")) {
            if (containChar(s, "e") > 1)
                return false;
            return isNumberValid(s, true, 'e');


        } else if (s.contains("E")) {
            if (containChar(s, "E") > 1)
                return false;
            return isNumberValid(s, true, 'E');

        } else if (s.contains(".")) {
            if (containChar(s, ".") > 1)
                return false;
            return isNumberValid(s, true, '.');

        } else {
            return isNumberValid(s, false, 'R');

        }


    }


    public boolean isNumberValid(String s, boolean isDecimal, char decimalNumber) {
        int positive = '+';
        int negative = '-';
        int zero = '0';
        int nine = '9';
        Character lastChar = null;
        boolean numberStart = false;
        boolean foundDecimal = false;
        boolean numberEnd = false;
        boolean sign = false;
        char character[] = s.toCharArray();
        for (char digit : character) {
            if ((!sign || (foundDecimal && (decimalNumber == 'e' || decimalNumber == 'E'))) && (digit == positive || digit == negative)) {
                sign = true;

                if ((lastChar.charValue() >= zero && lastChar <= nine)) {
                    return false;
                }

            } else if ((digit >= zero && digit <= nine)) {
                sign = true;
                numberStart = !foundDecimal ? true : numberStart;
                numberEnd = foundDecimal ? true : false;


            } else if (isDecimal && digit == '.') {

                if (foundDecimal && (decimalNumber == 'e' || decimalNumber == 'E'))
                    return false;

                sign = true;

            } else if (isDecimal && digit == decimalNumber) {
                foundDecimal = true;

            } else {
                return false;
            }
            lastChar = digit;
        }

        return decimalNumber == 'e' || decimalNumber == 'E' ? (numberStart && numberEnd) : numberStart || numberEnd;
    }

}
