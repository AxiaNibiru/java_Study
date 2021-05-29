package com.stackTrace;

import java.util.Scanner;

/**
 * 利用StackWalker类的一些方法可以得到所执行代码的文件名和行号, 以及类对象和方法名
 * 本类打印了递归函数的堆栈轨迹
 * @version 1.3.00 2021-4-11
 * @author axiaNibiru
 * */
public class StackTraceTest {
    /**
     * @param n 传入的参数n用于计算n*(n-1)*...*1
     * @return 返回n*(n-1)*...*1
     * */
    public static int factorial(int n){
        System.out.println("factorial(" + n + "):");
        var walker = StackWalker.getInstance();//使用StackWalker类得到一个StackWalker.StackFrame实例流, 其中每个实例描述一个栈帧
        walker.forEach(System.out::println);//将栈帧中的数据进行输出
        /*
        * 也可以使用以下方法
        * var t = new Throwable();//构建一个Throwable类实例
        * t.printStackTrace(new PrintWriter(out));//使用实例的printStackTrace()方法访问对战轨迹的文本描述信息
        * String description = out.toString();//将信息进行输出
        * */
        int r = 0;//建立int类型标记递归过程中相乘得到的数
        if (n <= 1)r = 1;//若递归到临界点r=1, 并直接返回1
        else r = n * factorial(n - 1);//若递归未完成则继续返回factorial(n-1)直到n<=1;
        return r;
    }

    public static void main(String[] args) {
        try(var in = new Scanner(System.in)){
            System.out.print("Enter n:");
            int n = in.nextInt();
            factorial(n);
        }
    }
}
