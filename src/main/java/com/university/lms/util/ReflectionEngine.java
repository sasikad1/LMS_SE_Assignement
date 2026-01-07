package com.university.lms.util;

import java.lang.reflect.*;

public class ReflectionEngine {

    public static void runComprehensiveTest(String className) {
        try {
            Class<?> clazz = Class.forName(className);
            System.out.println("Inspecting Class: " + clazz.getName());


            Constructor<?>[] constructors = clazz.getDeclaredConstructors();
            for (Constructor<?> c : constructors) {
                System.out.println("Constructor found: " + c.getName() + " with " + c.getParameterCount() + " parameters.");
            }

            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                System.out.println("Field: " + field.getName() + " [Type: " + field.getType().getSimpleName() + "]");
            }

            Method[] methods = clazz.getDeclaredMethods();
            for (Method m : methods) {
                System.out.println("Method: " + m.getName() + " [Returns: " + m.getReturnType().getSimpleName() + "]");
            }

        } catch (ClassNotFoundException e) {
            System.err.println("Class not found: " + e.getMessage());
        }
    }
}