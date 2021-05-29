package com.generic.genericReflection;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.Scanner;

public class GenericReflectionTest {
    public static void main(String[] args) {
        String name;
        if (args.length > 0) name= args[0];
        else {
            try(var in = new Scanner(System.in)){
                System.out.println("Enter Class name (e.g., java.util.Collections): ");
                name = in.next();
            }
        }

        try {
            Class<?> cl = Class.forName(name);
            printClass(cl);
            for (Method m :
                    cl.getDeclaredMethods()) {
                printMethod(m);
            }
        }catch (ClassNotFoundException E){
            E.printStackTrace();
        }
    }

    public static void printClass(Class<?> cl){
        System.out.print(cl);
        printTypes(cl.getTypeParameters(), "<" , ", ", ">", true);
        Type sc = cl.getGenericSuperclass();
        if (sc != null){
            System.out.print("extends");
            printType(sc, false);
            printTypes(cl.getGenericInterfaces(), " implements ", ", ", "", false);
            System.out.println();
        }

    }
    public static void printMethod(Method method){
        String name = method.getName();
        System.out.print(Modifier.toString(method.getModifiers()));
        System.out.print(" ");
        System.out.println();
    }
    public static void printTypes(Type[] types, String pre, String sep, String suf, boolean idDefinition){

    }
    public static void printType(Type type, boolean isDefinition){

    }
}
