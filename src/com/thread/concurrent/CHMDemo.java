package com.thread.concurrent;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 本类用并发散列映射统计一个目录树的java文件的所有单词
 * @author axiaNibiru
 * @version 1.3.00
 * */
public class CHMDemo {
    //创建一个并发散列实例
    public static ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<>();
    public CHMDemo(){
        
    }
}
