package com.basic.testInput.string;
import java.io.*;
import java.util.*;
/**
 * This program demonstrates a triangular array
 * @version 1.3.00 2021-03-23
 * @author axiaNibiru
 * */
public class StringInput {

    public static void main(String[] args) {
        System.out.print("输入1账户账号: ");
        Scanner scanner = new Scanner(System.in);
        String userName1 = scanner.next();
        System.out.print("输入账户1密码: ");
        String password1 = scanner.next();
        System.out.println();
        Console console = System.console();
        if (console == null){
            System.out.println("console为空");//jvm虚拟机底层没有提供Console, 所以这里为空
        }
        System.out.println("账户1账号: " + userName1);
        System.out.println("账户1密码: " + password1);
    }
}
