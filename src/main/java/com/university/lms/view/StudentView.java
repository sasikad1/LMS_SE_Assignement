package com.university.lms.view;

import com.university.lms.controller.StudentController;
import com.university.lms.model.Student;
import com.university.lms.service.StudentService;

import java.util.List;
import java.util.Scanner;

public class StudentView {

    private final Scanner scanner;
    private final StudentController studentController;

    // Menu Constants
    private static final int ADD_STUDENT = 1;
    private static final int LIST_STUDENTS = 2;
    private static final int UPDATE_STUDENT = 3;
    private static final int DELETE_STUDENT = 4;
    private static final int SEARCH_BY_NAME = 5;
    private static final int LIST_ACTIVE = 6;
    private static final int LIST_SORTED = 7;
    private static final int EXIT = 0;

    public StudentView(Scanner scanner, StudentService studentService) {
        this.scanner = scanner;
        this.studentController = new StudentController(studentService);
    }

    public void start() {
        while (true) {
            printMenu();
            int choice = promptInt("Enter choice: ", 0, 7); // Max අගය 7 දක්වා වැඩි කළා

            switch (choice) {
                case ADD_STUDENT -> addStudent();
                case LIST_STUDENTS -> listStudents();
                case UPDATE_STUDENT -> updateStudent();
                case DELETE_STUDENT -> deleteStudent();
                case SEARCH_BY_NAME -> searchStudentByName();
                case LIST_ACTIVE -> listActiveStudents();
                case LIST_SORTED -> listSortedStudents(); // Controller එකේ getSortedStudentList() call කරයි
                case EXIT -> {
                    System.out.println("Back to Main Menu.");
                    return;
                }
                default -> System.out.println("Unknown choice.");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n--- Student Management Menu ---");
        System.out.println(ADD_STUDENT + ". Add Student");
        System.out.println(LIST_STUDENTS + ". List All Students");
        System.out.println(UPDATE_STUDENT + ". Update Student");
        System.out.println(DELETE_STUDENT + ". Delete Student");
        System.out.println(SEARCH_BY_NAME + ". Search by Name (Stream Filter)");
        System.out.println(LIST_ACTIVE + ". List Active Students");
        System.out.println(LIST_SORTED + ". List Students Sorted by Name (Functional)");
        System.out.println(EXIT + ". Back");
    }

    // 1. Add Student
    private void addStudent() {
        int id = promptInt("ID: ");
        String name = promptString("Name: ");
        String email = promptString("Email: ");
        if (studentController.addStudent(new Student(id, name, email))) {
            System.out.println("Student added successfully.");
        } else {
            System.out.println("Student ID already exists.");
        }
    }

    // 2. List All Students
    private void listStudents() {
        printList(studentController.getAllStudents(), "--- All Students ---");
    }

    // 3. Update Student
    private void updateStudent() {
        int id = promptInt("Student ID: ");
        Student student = studentController.getStudentById(id); // Controller එකේ method එක call කිරීම

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        updateStudentDetails(student);
        studentController.updateStudent(student); // Controller එකේ update call කිරීම
        System.out.println("Student updated successfully.");
    }

    private void updateStudentDetails(Student student) {
        String name = promptString("New name (blank to keep): ");
        String email = promptString("New email (blank to keep): ");
        if (!name.isBlank()) student.setName(name);
        if (!email.isBlank()) student.setEmail(email);
    }

    // 4. Delete Student
    private void deleteStudent() {
        int id = promptInt("Student ID: ");
        System.out.print("Are you sure? (Y/N): ");
        String confirm = scanner.nextLine().trim().toLowerCase();

        if (confirm.equals("y") || confirm.equals("yes")) {
            if (studentController.deleteStudent(id)) {
                System.out.println("Student deleted successfully.");
            } else {
                System.out.println("Student not found.");
            }
        } else {
            System.out.println("Delete cancelled.");
        }
    }

    // 5. Search by Name (Functional)
    private void searchStudentByName() {
        String keyword = promptString("Name keyword: ");
        // Controller එකේ stream filter logic එක මෙහිදී ක්‍රියාත්මක වේ
        List<Student> result = studentController.searchStudentsByName(keyword);
        printList(result, "--- Search Results ---");
    }

    // 6. List Active Students
    private void listActiveStudents() {
        printList(studentController.getActiveStudents(), "--- Active Students ---");
    }

    // 7. List Sorted Students (Functional)
    private void listSortedStudents() {
        List<Student> sorted = studentController.getSortedStudentList();
        printList(sorted, "--- Students Sorted by Name ---");
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
            if (value >= min && value <= max) return value;
            System.out.println("Please enter a number between " + min + " and " + max + ".");
        }
    }

    private String promptString(String msg) {
        System.out.print(msg);
        return scanner.nextLine().trim();
    }

    private <T> void printList(List<T> list, String title) {
        System.out.println("\n" + title);
        if (list.isEmpty()) {
            System.out.println("No records found.");
        } else {
            list.forEach(System.out::println);
        }
    }

}