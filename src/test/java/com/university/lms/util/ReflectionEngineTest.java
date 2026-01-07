package com.university.lms.util;

import static org.junit.jupiter.api.Assertions.*;

import com.university.lms.util.ReflectionEngine;

public class ReflectionEngineTest {
    public static void main(String[] args) {
        System.out.println("=== COMPREHENSIVE REFLECTION TEST START ===");

        System.out.println("\n--- Testing Model Layer ---");
        ReflectionEngine.runComprehensiveTest("com.university.lms.model.Student");

        System.out.println("\n--- Testing Service Layer ---");
        ReflectionEngine.runComprehensiveTest("com.university.lms.service.impl.StudentServiceImpl");

        System.out.println("\n=== TEST COMPLETED SUCCESSFULLY ===");
    }
}