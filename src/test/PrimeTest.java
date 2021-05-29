package test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class PrimeTest {
    public static void main(String[] args) {
        PrimeTest.findPrimeWithList(20000000);
    }

    /**
     * @param number 输入的需要查找的数
     * */
    public static List<Integer> findPrimeWithList(int number){
        if (number < 2) return new LinkedList<Integer>();
        var list = new LinkedList<Integer>();
        list.add(2);
        var time1 = System.currentTimeMillis();
        for (int i = 3; i <= number; i++) {
            var flag = true;
            if (i%2 != 0){
                for (int j = 3; j <= Math.sqrt(i); j+=2) {
                    if (i%j == 0){
                        flag = false;
                        break;
                    }
                }
                if (flag){
                    list.add(i);
                }
            }
        }


        System.out.println(list.toString());
        var time2 =  System.currentTimeMillis() - time1 ;
        System.out.println(time2);
        return list;
    }

    @Override
    public String toString(){
        return super.getClass().getName();
    }

    public int[] findPrime(int n){
        var array = new int[50];
        var count = 0;
        if(n<2){
            return array;
        }
        array[count++] = 2;
        for(var i = 3; i <= n; i++){
            var flag = 1;
            if(i %2 != 0){
                for(var j = 3; j <= Math.sqrt(i); j+=2){
                    if(i%j==0){
                        flag = 0;
                        break;
                    }
                }
                if(flag == 1){
                    array[count++] = i;
                }
            }
        }
        for (int number :
                array) {
            if (number != 0){
                System.out.print(number + " ");
            }
        }
        return array;
    }
}

