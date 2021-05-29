package com.objectAndClass.employee;

import java.time.LocalDate;
/**
 * 这个类用于测试类的构造器和私有字段和方法
 * @version 1.3.00 2021-3-27
 * @author axiaNibiru
 * */
public class Employee {
    private String name;
    private double salary;
    private LocalDate hireDay;
    public Employee(String name, double salary, int year, int month, int day){
        this.name = name;
        this.salary =salary;
        this.hireDay = LocalDate.of(year, month, day);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public LocalDate getHireDay() {
        return hireDay;
    }

    public void setHireDay(LocalDate hireDay) {
        this.hireDay = hireDay;
    }

    public void raiseSalary(double byPercent){
        double raise = salary * byPercent / 100;
        salary += raise;
    }
}
