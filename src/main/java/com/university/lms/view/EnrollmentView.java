package com.university.lms.view;

import com.university.lms.controller.EnrollmentController;
import com.university.lms.service.CourseService;
import com.university.lms.service.EnrollmentService;
import com.university.lms.service.StudentService;
import com.university.lms.service.impl.EnrollmentServiceImpl;

import java.util.List;
import java.util.Scanner;

public class EnrollmentView {

    private static final int ENROLL_ACTION = 1;
    private static final int LIST_ACTION = 2;
    private static final int DELETE_ACTION = 3;
    private static final int EXIT_ACTION = 0;

    private final Scanner scanner;
    private final EnrollmentController controller;
    private final StudentService studentService;
    private final CourseService courseService;



    public EnrollmentView(Scanner scanner, EnrollmentService enrollmentService,
                          StudentService studentService, CourseService courseService) {
        this.scanner = scanner;
        this.studentService = studentService;
        this.courseService = courseService;
        this.controller = new EnrollmentController(enrollmentService);
    }

    public void start() {
        while (true) {
            printMenu();
            int choice = promptInt("Enter choice: ", 0, 3);

            switch (choice) {
                case ENROLL_ACTION -> enrollStudent();
                case LIST_ACTION -> listEnrollments();
                case DELETE_ACTION -> deleteEnrollment();
                case EXIT_ACTION -> {
                    System.out.println("Back to Main Menu.");
                    return;
                }
                default -> System.out.println("Unknown choice.");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n=== Enrollment Menu ===");
        System.out.println("1. Enroll Student");
        System.out.println("2. List Enrollments");
        System.out.println("3. Remove Enrollment");
        System.out.println("0. Back");
    }

    private void enrollStudent() {
        System.out.println("\n=== Enroll Student ===");

        // Show available students
        System.out.println("Available Students:");
        studentService.getAllStudents().forEach(s ->
                System.out.println("  ID: " + s.getId() + " | Name: " + s.getName()));

        // Show available courses
        System.out.println("\nAvailable Courses:");
        courseService.getAllCourses().forEach(c ->
                System.out.println("  ID: " + c.getId() + " | " + c.getTitle()));

        System.out.println();
        int studentId = promptInt("Student ID: ");
        int courseId = promptInt("Course ID: ");

        boolean success = controller.enrollStudent(studentId, courseId);

        if (success) {
            System.out.println("✅ Enrollment successful!");
        } else {
            System.out.println("❌ Enrollment failed. Check if student/course exists.");
        }
    }

    private void listEnrollments() {
        List<?> enrollments = controller.getAllEnrollments();
        printList(enrollments, "--- Enrollments ---");
    }

    private void deleteEnrollment() {
        int studentId = promptInt("Student ID: ");
        int courseId = promptInt("Course ID: ");
        boolean success = controller.removeEnrollment(studentId, courseId);

        if (success)
            System.out.println("Enrollment removed successfully.");
        else
            System.out.println("Enrollment not found.");
    }

    // ---------------- Helper Methods ----------------
    private int promptInt(String msg) {
        while (true) {
            System.out.print(msg);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Try again.");
            }
        }
    }

    private int promptInt(String msg, int min, int max) {
        while (true) {
            int value = promptInt(msg);
            if (value < min || value > max) {
                System.out.println("Enter a number between " + min + " and " + max + ".");
            } else return value;
        }
    }

    private <T> void printList(List<T> list, String title) {
        System.out.println(title);
        if (list.isEmpty()) System.out.println("No records found.");
        else list.forEach(System.out::println);
    }
}
