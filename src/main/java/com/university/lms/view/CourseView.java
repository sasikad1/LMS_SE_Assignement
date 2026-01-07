package com.university.lms.view;

import com.university.lms.controller.CourseController;
import com.university.lms.model.Course;
import com.university.lms.service.CourseService;
import com.university.lms.service.impl.CourseServiceImpl;

import java.util.List;
import java.util.Scanner;

public class CourseView {

    private final Scanner scanner;
    private final CourseController controller;

    private static final int ADD_COURSE = 1;
    private static final int LIST_COURSES = 2;
    private static final int UPDATE_COURSE = 3;
    private static final int DELETE_COURSE = 4;
    private static final int EXIT = 0;

    // Constructor with dependency injection
    public CourseView(Scanner scanner, CourseService courseService) {
        this.scanner = scanner;
        this.controller = new CourseController(courseService);
    }

    // Alternative constructor for backward compatibility
    public CourseView(Scanner scanner) {
        this(scanner, new CourseServiceImpl());
    }


    public void start() {
        while (true) {
            printMenu();
            int choice = promptInt("Enter choice: ", 0, 4);
            switch (choice) {
                case ADD_COURSE -> addCourse();
                case LIST_COURSES -> listCourses();
                case UPDATE_COURSE -> updateCourse();
                case DELETE_COURSE -> deleteCourse();
                case EXIT -> { System.out.println("Back to Main Menu."); return; }
            }
        }
    }

    private void printMenu() {
        System.out.println("\n=== Course Menu ===");
        System.out.println("1. Add Course");
        System.out.println("2. List Courses");
        System.out.println("3. Update Course");
        System.out.println("4. Delete Course");
        System.out.println("0. Back");
    }

    private void addCourse() {
        int id = promptInt("Course ID: ");
        String title = promptString("Title: ");
        String description = promptString("Description: ");
        int credits = promptInt("Credits: ", 1, Integer.MAX_VALUE);
        String instructor = promptString("Instructor: ");

        Course course = new Course(id, title, description, credits, instructor);
        boolean success = controller.addCourse(course);
        System.out.println(success ? "Course added successfully." : "Course ID already exists.");
    }

    private void listCourses() {
        printList(controller.getAllCourses(), "--- All Courses ---");
    }

    private void updateCourse() {
        int id = promptInt("Course ID to update: ");
        Course existing = controller.getCourseById(id);
        if (existing == null) {
            System.out.println("Course not found.");
            return;
        }

        String title = promptString("New Title (blank to keep): ");
        String description = promptString("New Description (blank to keep): ");
        String instructor = promptString("New Instructor (blank to keep): ");
        String creditsStr = promptString("New Credits (blank to keep " + existing.getCredits() + "): ");

        if (!title.isBlank()) existing.setTitle(title);
        if (!description.isBlank()) existing.setDescription(description);
        if (!instructor.isBlank()) existing.setInstructor(instructor);
        if (!creditsStr.isBlank()) {
            try {
                int credits = Integer.parseInt(creditsStr);
                existing.setCredits(credits);
            } catch (NumberFormatException e) {
                System.out.println("Invalid credits, keeping previous value.");
            }
        }

        boolean success = controller.updateCourse(existing);
        System.out.println(success ? "Course updated successfully." : "Failed to update course.");
    }

    private void deleteCourse() {
        int id = promptInt("Course ID to delete: ");
        boolean success = controller.deleteCourse(id);
        System.out.println(success ? "Course deleted successfully." : "Course not found.");
    }

    // ------------------ Helpers ------------------
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
            int val = promptInt(msg);
            if (val < min || val > max) {
                System.out.println("Enter between " + min + " and " + max + ".");
            } else return val;
        }
    }

    private String promptString(String msg) {
        System.out.print(msg);
        return scanner.nextLine().trim();
    }

    private <T> void printList(List<T> list, String title) {
        System.out.println(title);
        if (list.isEmpty()) System.out.println("No courses found.");
        else list.forEach(System.out::println);
    }
}
