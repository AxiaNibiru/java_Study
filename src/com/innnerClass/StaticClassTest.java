package com.innnerClass;

public class StaticClassTest {
    public static void main(String[] args) {
        var values = new double[20];//创建要测试的数组
        for (int i = 0; i < values.length; i++) {
            values[i] = Math.random() * 100;//对数组中的值进行赋值
        }
        ArrayAlg.Pair pair = ArrayAlg.minmax(values);
        System.out.println("min = " + pair.getFirst());
        System.out.println("max = " + pair.getSecond());
    }

}
class ArrayAlg{
    //静态内部类比较的同时通过构造Pair类一次性返回最大值和最小值
    public static Pair minmax(Object values){
        double[] doubles = (double[])values;
        double first = Double.MAX_VALUE;
        double second = Double.MIN_VALUE;
        for (double number:
        doubles){
            if (number < first)first = number;
            if (number > second)second = number;
        }
        return new Pair(first, second);
    }

    //该类不需要外部类引用所以声明为静态内部类
    public static class Pair{
        private double first;
        private double second;

        //构建初始化first和second属性的构造函数
        public Pair(double first, double second){
            this.first = first;
            this.second = second;
        }

        public Pair(){
        }

        public double getFirst() {
            return first;
        }

        public double getSecond() {
            return second;
        }
    }

}