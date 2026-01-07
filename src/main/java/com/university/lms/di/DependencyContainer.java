//package com.university.lms.di;
//
//import com.university.lms.service.*;
//import com.university.lms.service.impl.*;
//import com.university.lms.view.*;
//import java.util.Scanner;
//
//public class DependencyContainer {
//
//    private static DependencyContainer instance;
//
//    // Core dependencies
//    private Scanner scanner;
//
//    // Services
//    private StudentService studentService;
//    private CourseService courseService;
//    private EnrollmentService enrollmentService;
//
//    // Views
//    private StudentView studentView;
//    private CourseView courseView;
//    private EnrollmentView enrollmentView;
//    private ConsoleView consoleView;
//
//    // Private constructor - Singleton pattern
//    private DependencyContainer() {
//        initializeDependencies();
//    }
//
//    // Singleton instance getter
//    public static DependencyContainer getInstance() {
//        if (instance == null) {
//            instance = new DependencyContainer();
//        }
//        return instance;
//    }
//
//    // Initialize all dependencies
//    private void initializeDependencies() {
//        System.out.println("Initializing Dependency Container...");
//
//        // 1. Initialize Scanner (shared resource)
//        this.scanner = new Scanner(System.in);
//
//        // 2. Initialize Services
//        this.studentService = createStudentService();
//        this.courseService = createCourseService();
//        this.enrollmentService = createEnrollmentService();
//
//        // 3. Initialize Views with injected dependencies
//        this.studentView = createStudentView();
//        this.courseView = createCourseView();
//        this.enrollmentView = createEnrollmentView();
//
//        // 4. Initialize Main ConsoleView
//        this.consoleView = createConsoleView();
//
//        System.out.println("Dependency Container initialized successfully!");
//    }
//
//    // Service creation methods
//    private StudentService createStudentService() {
//        return new StudentServiceImpl();
//    }
//
//    private CourseService createCourseService() {
//        return new CourseServiceImpl();
//    }
//
//    private EnrollmentService createEnrollmentService() {
//        // Note: EnrollmentService needs other services as dependencies
//        return new EnrollmentServiceImpl(studentService, courseService);
//    }
//
//    // View creation methods with DI
//    private StudentView createStudentView() {
//        return new StudentView(scanner, studentService);
//    }
//
//    private CourseView createCourseView() {
//        return new CourseView(scanner, courseService);
//    }
//
//    private EnrollmentView createEnrollmentView() {
//        return new EnrollmentView(scanner, enrollmentService, studentService, courseService);
//    }
//
//    private ConsoleView createConsoleView() {
//        return new ConsoleView(scanner, studentView, courseView, enrollmentView);
//    }
//
//    // Public getter methods for accessing dependencies
//    public Scanner getScanner() {
//        return scanner;
//    }
//
//    public StudentService getStudentService() {
//        return studentService;
//    }
//
//    public CourseService getCourseService() {
//        return courseService;
//    }
//
//    public EnrollmentService getEnrollmentService() {
//        return enrollmentService;
//    }
//
//    public StudentView getStudentView() {
//        return studentView;
//    }
//
//    public CourseView getCourseView() {
//        return courseView;
//    }
//
//    public EnrollmentView getEnrollmentView() {
//        return enrollmentView;
//    }
//
//    public ConsoleView getConsoleView() {
//        return consoleView;
//    }
//
//    // Cleanup method to release resources
//    public void cleanup() {
//        System.out.println("Cleaning up resources...");
//        if (scanner != null) {
//            scanner.close();
//        }
//        // Add cleanup for other resources if needed
//    }
//
//    // Reset method for testing purposes
//    public static void reset() {
//        if (instance != null) {
//            instance.cleanup();
//            instance = null;
//        }
//    }
//}