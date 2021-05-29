package com.inherit.equals;


/**
 * This Program demonstrates the equals method
 * @version 1.3.00 2021-3-30
 * @author axiaNibiru
 * */

public class EqualsTest {
    public static void main(String[] args) {
        var alice1 = new Employee("Alice Adams", 75000, 1987, 12, 15);
        var alice2 = alice1;
        var alice3 = new Employee("Alice Adams", 75000, 1987, 12, 15);
        var bob = new Employee("Bob Brandson", 50000, 1989, 10, 1);
        System.out.println("alice1 == alice2: " + (alice1 == alice2));//true
        System.out.println("alice1 == alice3: " + (alice1 == alice3));//false
        System.out.println("alice1.equals(alice3): " + (alice1.equals(alice3)));//true
        System.out.println("alice1.equals(bob): " + (alice1.equals(bob)));//false

        var varl = new Manager("Carl Cracker", 80000, 1987, 12, 15);
        var boss = new Manager("Carl Cracker", 80000, 1987, 12, 15);
        boss.setBonus(5000);
        System.out.println("boss.toString(): " + boss);
        System.out.println("carl.equals(boss): " + varl.equals(boss));//false
        System.out.println("alice1.hashCode(): " + alice1.hashCode());
        System.out.println("alice3.hashCode(): " + alice3.hashCode());//alice1.hashCode() == alice3.hashCode()
        System.out.println("bob.hashCode(): " + bob.hashCode());
        System.out.println("carl.hashCode(): " + varl.hashCode());
    }
}
