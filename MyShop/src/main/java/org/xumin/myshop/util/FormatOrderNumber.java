package org.xumin.myshop.util;

public class FormatOrderNumber {
    public static String LPAD(String str, int length, String padChar) {
        if (str.length() >= length) {
            return str;
        }
        return "FSX"+String.valueOf(padChar).repeat(length - str.length()) + str;
    }
}
