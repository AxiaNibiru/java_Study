package com.basic.testString;
/**
 * This program demonstrates a triangular array
 * @version 1.3.00 2021-03-22
 * @author axiaNibiru
 * */
public class TestString {
    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder();
        String ch = "ch";
        String str = "str";
        stringBuilder.append(ch);
        stringBuilder.append(str);
        String completedString  = stringBuilder.toString();
        System.out.println(completedString);
    }
}
