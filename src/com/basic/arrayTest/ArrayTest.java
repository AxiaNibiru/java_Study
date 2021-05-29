package com.basic.arrayTest;
/**
 * This program demonstrates a triangular array
 * @version 1.3.00 2021-03-24
 * @author axiaNibiru
 * */
public class ArrayTest {
    public static void main(String[] args) {
        final int MAX = 9;
        //定义二维数字
        int odds[][] = new int[MAX][];
        for (int i = 0; i < MAX; i++) {
            odds[i] = new int[i + 1];
        }
        //填充数组
        for (int i = 0; i < odds.length; i++) {
            for (int j = 0; j < odds[i].length; j++) {
                /*
                * 对i+1个数中能抽中j+1个数的概率进行计算
                * */
                int lotteryOdds = 1;
                for (int k = 1; k <= j; k++) {
                    lotteryOdds = lotteryOdds * (i - k + 1)/ k;
                }
                odds[i][j] = lotteryOdds;
            }
        }
        //打印数组
        for (int[] row :
                odds) {
            for (int odd :
                    row) {
                System.out.printf("%4d", odd);
            }
            System.out.println();
        }
    }
}
