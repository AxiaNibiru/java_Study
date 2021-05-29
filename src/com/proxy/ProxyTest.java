package com.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Random;
/**
 * 该类展现的如何代理一个类并进行追踪
 * @version 1.3.00
 * @author axiaNibiru
 * */
public class ProxyTest {
    public static void main(String[] args) {
        var elements = new Object[1000];//创声明需要代理的对象
        for (int i = 0; i < elements.length; i++) {
            Integer integer = i + 1;//填充需要代理的对象
            var handler = new TraceHandler(integer);//将对象进行包装
            Object proxy = Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{//创建代理类(实现代理)->(类加载器, 指定的接口, 代理类包装后的对象)
                    Comparable.class
            }, handler);
            elements[i] = proxy;//让元素引用代理后的对象
        }
        Integer key = new Random().nextInt(elements.length) + 1;//创建随机包装整形数
        int result = Arrays.binarySearch(elements, key);//使用二分查找在数组中搜索该整形数
        if (result >= 0){
            System.out.println(elements[result]);
        }
    }
}
class TraceHandler implements InvocationHandler {
    private Object target;
    public TraceHandler(Object t){
        target = t;
    }//构造函数中初始化需要包装的对象

    /**
     * 每次调用该对象都会使用invoke追踪其方法
     * @param proxy 需要追踪的对象
     * @param method 调用对象所使用的方法
     * @param args 方法中的各个参数
     * */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.print(target);//打印追踪的对象
        System.out.print("." + method.getName() + "(");//打印调用该类的方法名
        if (args != null){//若调用方法中参数不为空
            for (int i = 0; i < args.length; i++) {//依次输出其参数值
                System.out.print(args[i]);
                if (i < args.length - 1) System.out.print(", ");
            }
        }
        System.out.println(")");
        return method.invoke(target, args);
    }
}
