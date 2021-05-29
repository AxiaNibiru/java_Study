package com.inherit.abstractClasses;

/**
 * 这个类用来描述抽象类型
 * @version 1.3.00 2021-3-29
 * @author axiaNbiru
 * */
public class PersonTest {
    public static void main(String[] args) {
        var people = new Person[2];

        people[0] = new Employee("Harry Hacker", 50000, 1989, 10, 1);
        people[1] = new Student("Maria Mirris", "computer science");

        for (Person p :
                people) {
            System.out.println(p.getName() + ", " + p.getDescription());
        }


    }
}
