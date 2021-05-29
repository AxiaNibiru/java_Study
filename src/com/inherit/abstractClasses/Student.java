package com.inherit.abstractClasses;

public class Student extends Person{
    private String major;
    /**
     * @param name the student's name;
     * @param major the student's major;
     * */
    public Student(String name, String major){
        super(name);
        this.major = major;
    }
    @Override
    public String getDescription() {
        return "a student majoring in" + major;
    }

    public String getMajor() {
        return major;
    }

    @Override
    public String toString(){
        return super.toString() + " [ name = " + getName() + " major = " + major + " ] ";
    }
}
