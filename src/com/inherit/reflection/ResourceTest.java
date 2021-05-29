package com.inherit.reflection;

import javax.swing.*;
import java.io.InputStream;
import java.net.URL;

/**
 * 本实例重点在: URL getResource(String name);->根据地址描述获得资源url  InputStream getResourceAsStream(String name);->根据地址获得输入流
 * @version 1.3.00 2021-4-6
 * @author axiaNibiru
 * */
public class ResourceTest {
    public static void main(String[] args) {
        try{
            Class cl = ResourceTest.class;//获取拥有资源的类的对象
            URL url = cl.getResource("./resource/gif_01.gif");//接受描述资源位置的URL
            var icon = new ImageIcon(url);//根据描述资源位置的URL实例化弹出窗口对象

            InputStream stream = cl.getResourceAsStream("resource/resource.txt");//根据描述的资源位置获取输入流
            var about = new String(stream.readAllBytes(), "UTF-8");//将资源转化成二进制后以UTF-8转化成字符串
            JOptionPane.showMessageDialog(null, about, about, JOptionPane.INFORMATION_MESSAGE, icon);//再容器中展示窗口界面(包含gif图片和说明)
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
