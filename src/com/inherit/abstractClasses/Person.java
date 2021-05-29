package com.inherit.abstractClasses;

public abstract class Person {
    private String name;
    public Person (String name){
        this.name = name;
    }

    public abstract String getDescription();

    public String getName() {
        return name;
    }
    @Override
    public String toString(){
        return getClass().getName();
    }
}
