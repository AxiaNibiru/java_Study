package com.interfaces;

import javax.swing.*;
import java.util.Arrays;
import java.util.Date;

public class LambdaTest {
    public static void main(String[] args) {
        var plantes = new String[]{"Mercury", "Venus", "Earth", "Mars"};
        System.out.println(Arrays.toString(plantes));
        System.out.println("Sort in dictionary order");
        Arrays.sort(plantes);//按字典排序
        System.out.println(Arrays.toString(plantes));
        System.out.println("Sort by Length");
        Arrays.sort(plantes, (first, second)->first.length() - second.length());//lambda按长度排序
        System.out.println(Arrays.toString(plantes));
         var timer = new Timer(1000, event -> {
             System.out.println("The Time is " + new Date());//Timer利用lambda建立事件

         });
        timer.start();
        JOptionPane.showMessageDialog(null, "Quit program? ");
        System.exit(0);
    }
}
