package com.inherit.abstractClasses;

import java.time.LocalDate;

public class Employee extends Person{
    private double salary;
    private LocalDate hireDay;

    public Employee(String name, double salary, int year, int month, int day){
        super(name);
        this.salary = salary;
        hireDay = LocalDate.of(year, month, day);
    }
    @Override
    public String getDescription() {
        return String.format("an employee with a salary of $.2f", salary);
    }

    public double getSalary(){
        return salary;
    }

    public LocalDate getHireDay() {
        return hireDay;
    }

    public void raiseSalary(double buPercent){
        double raise = salary * buPercent / 100;
        salary += raise;
    }
    @Override
    public String toString(){
        return super.toString() + " [ name = " + getName() + " salary = " + salary + " hireDay = " + hireDay + " ]";
    }
}
