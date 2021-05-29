package com.interfaces;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;

public class TimePrinter implements ActionListener {//构造一个触发时发出响声的动作事件

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("At the tone, the time is " + Instant.ofEpochMilli(e.getWhen()));//取得当前时间, 并输出
        Toolkit.getDefaultToolkit().beep();//获得默认工具箱中发出一声鸣响的工具(发出鸣响)
    }
}
