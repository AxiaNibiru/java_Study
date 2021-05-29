package com.interfaces;

import javax.swing.*;

public class TimerTest {
    public static void main(String[] args) {
        var listener = new TimePrinter();
        var timer = new Timer(1000, listener);//构造一个定时器每隔1000毫秒触发一次动作
        timer.start();//开启定时器
        JOptionPane.showMessageDialog(null, "Quit program?");//api停留在swing组件上直到点击按钮(期间每隔一秒发出声音)
        System.exit(0);//进程结束
    }
}
