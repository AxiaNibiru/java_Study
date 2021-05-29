package com.interfaces;

import com.interfaces.Employee;
import java.util.Arrays;

public class EmployeeSortTest {
    public static void main(String[] args) {
        var staffs = new Employee[3];
        staffs[0] = new Employee("HarryHacker", 35000, 2020, 1,1);
        staffs[1] = new Employee("Carl Cracker", 75000, 2021, 4, 6);
        staffs[2] = new Employee("Tony Tester", 38000, 1955, 3, 15);
        Arrays.sort(staffs);//对数组中的元素进行排序, 但是要求数组元素必须实现Comparable接口的类, 并且元素之间是可以比较的;
        for (Employee staff:
             staffs) {
            System.out.println(staff.getName()+ staff.getSalary() + staff.getHireDay());
        }
    }
}
