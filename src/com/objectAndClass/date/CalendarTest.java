package com.objectAndClass.date;

import java.time.DayOfWeek;
import java.time.LocalDate;
/**
 * 这个程序用于显示当前月份日历(阳历)
 * @version 1.3.00 2021-3-26
 * @author axiaNibiru
 * */
public class CalendarTest {
    public static void main(String[] args) {
        LocalDate localDate = LocalDate.now();
        int month = localDate.getMonthValue();
        int today = localDate.getDayOfMonth();
        //得到当前几月和当月天数之后. 重设置日期为1号
         localDate = localDate.minusDays(today - 1);
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        int week = dayOfWeek.getValue();

        System.out.println("Mon Tue Thu Fir Sat Sun ");
        for (int i = 0; i < week; i++) {
            System.out.println("    ");
        }
        while (localDate.getMonthValue() == month){
            System.out.printf("%3d", localDate.getDayOfMonth());
            if (localDate.getDayOfMonth() == today){
                System.out.print("*");
            }else {
                System.out.print(" ");
            }
            localDate = localDate.plusDays(1);
            if (localDate.getDayOfWeek().getValue() == 1){
                System.out.println();
            }
        }
        if (localDate.getDayOfWeek().getValue() != 1){
            System.out.println();
        }
    }
}
