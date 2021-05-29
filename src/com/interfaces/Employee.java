package com.interfaces;

public class Employee extends com.objectAndClass.employee.Employee implements Comparable<Employee>{
    public Employee(String name, double salary, int year, int month, int day) {
        super(name, salary, year, month, day);
    }

    @Override
    public int compareTo(Employee o) {
        if (getClass() != o.getClass()) throw new ClassCastException();
        return Double.compare(super.getSalary(), o.getSalary());
    }
}
