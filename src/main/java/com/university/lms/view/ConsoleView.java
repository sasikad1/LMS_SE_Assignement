package com.university.lms.view;

import java.util.Scanner;

import com.university.lms.service.CourseService;
import com.university.lms.service.EnrollmentService;
import com.university.lms.service.StudentService;
import com.university.lms.service.impl.*;

public class ConsoleView {

    private final Scanner scanner = new Scanner(System.in);

    private static final int MENU_MIN = 0;
    private static final int MENU_MAX = 3;

    private static final int STUDENT_MGMT = 1;
    private static final int COURSE_MGMT = 2;
    private static final int ENROLLMENT_MGMT = 3;
    private static final int EXIT = 0;


    private final StudentService studentService = new StudentServiceImpl();
    private final CourseService courseService = new CourseServiceImpl();
    private final EnrollmentService enrollmentService;

    // Views - injected with dependencies
    private final StudentView studentView;
    private final CourseView courseView;
    private final EnrollmentView enrollmentView;

    public ConsoleView() {
        // Initialize enrollment service with its dependencies (Constructor Injection)
        this.enrollmentService = new EnrollmentServiceImpl(studentService, courseService);

        // Initialize views with their dependencies (Constructor Injection)
        this.studentView = new StudentView(scanner, studentService);
        this.courseView = new CourseView(scanner, courseService);
        this.enrollmentView = new EnrollmentView(scanner, enrollmentService,
                studentService, courseService); // Multiple dependencies injected
    }

    public void start() {
        while (true) {
            printMainMenu();
            int choice = promptInt("Enter choice: ", 0, 3);

            switch (choice) {
                case STUDENT_MGMT -> studentView.start();
                case COURSE_MGMT -> courseView.start();
                case ENROLLMENT_MGMT -> enrollmentView.start();
                case EXIT -> {
                    System.out.println("Goodbye!");
                    return;
                }
            }
        }
    }

    private void printMainMenu() {
        System.out.println("\n=== Main Menu ===");
        System.out.println("1. Student Management");
        System.out.println("2. Course Management");
        System.out.println("3. Enrollment Management");
        System.out.println("0. Exit");
    }

    private int promptInt(String msg, int min, int max) {
        while (true) {
            System.out.print(msg);
            try {
                int val = Integer.parseInt(scanner.nextLine());
                if (val < min || val > max) {
                    System.out.println("Enter between " + min + " and " + max + ".");
                    continue;
                }
                return val;
            } catch (NumberFormatException e) {
                System.out.println("Invalid number.");
            }
        }
    }
}