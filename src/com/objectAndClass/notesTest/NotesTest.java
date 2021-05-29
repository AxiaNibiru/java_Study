package com.objectAndClass.notesTest;

/**
 * {@code Card}这个类用来测试类注释的格式
 * @version 1.3.00 2021-3-28
 * @author axiaNibiru
 * @see <a href="./doc-files">test</a>
 * */
public class NotesTest {
    private static int staticId;

    static {
        staticId = 0;
        staticId++;
    }

    public String test;
    private String name;
    private int age;
    private String sex;
    private int id;
    public NotesTest(String name, String sex, int age){
        this(name, sex);
        this.age = age;
        id = staticId;
        staticId++;
    }

    public NotesTest(String name, String sex){
        this.name = name;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
