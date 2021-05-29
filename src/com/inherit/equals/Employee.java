package com.inherit.equals;

import java.time.LocalDate;
import java.util.Objects;

public class Employee {
    private String name;
    private double salary;
    private LocalDate hireDay;

    public Employee(String name, double salary, int year, int month, int day) {
        this.hireDay = LocalDate.of(year, month, day);
        this.name = name;
        this.salary = salary;
    }

    public LocalDate getHireDay() {
        return hireDay;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }
    public boolean equals(Object otherObject){
        if (this == otherObject) return true;
        if (otherObject == null) return false;
        if (getClass() != otherObject.getClass()) return false;
        var other = (Employee) otherObject;
        return Objects.equals(name, other.name) && salary == other.salary && Objects.equals(hireDay, other.hireDay);
    }

    public int hashCode(){
        return Objects.hash(name, salary, hireDay);
    }

    public String toString(){
        return getClass().getName() + "[ name = " + name + " salary = " + salary + " hireDay = " + hireDay+ " ] ";
    }
}
