package com.university.lms.view;

import com.university.lms.controller.StudentController;
import com.university.lms.model.Student;
import com.university.lms.service.StudentService;
import com.university.lms.service.impl.StudentServiceImpl;

import java.util.List;
import java.util.Scanner;

public class StudentView {

    private final Scanner scanner;
    private final StudentController studentController;

    private static final int ADD_STUDENT = 1;
    private static final int LIST_STUDENTS = 2;
    private static final int UPDATE_STUDENT = 3;
    private static final int DELETE_STUDENT = 4;
    private static final int SEARCH_BY_NAME = 5;
    private static final int LIST_ACTIVE = 6;
    private static final int EXIT = 0;

    private static final int MENU_MIN = 0;
    private static final int MENU_MAX = 6;

    public StudentView(Scanner scanner, StudentService studentService) {
        this.scanner = scanner;
        this.studentController = new StudentController(studentService);
    }

    public void start() {
        while (true) {
            printMenu();
            int choice = promptInt("Enter choice: ", 0, 6);

            switch (choice) {
                case ADD_STUDENT -> addStudent();
                case LIST_STUDENTS -> listStudents();
                case UPDATE_STUDENT -> updateStudent();
                case DELETE_STUDENT -> deleteStudent();
                case SEARCH_BY_NAME -> searchStudentByName();
                case LIST_ACTIVE -> listActiveStudents();
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
        System.out.println(SEARCH_BY_NAME + ". Search by Name");
        System.out.println(LIST_ACTIVE + ". List Active Students");
        System.out.println(EXIT + ". Back");
    }

    private void addStudent() {

        int id = promptInt("ID: ");
        String name = promptString("Name: ");
        String email = promptString("Email: ");

        Student student = new Student(id, name, email);

        boolean added = studentController.addStudent(student);

        if (added) {
            System.out.println("Student added successfully.");
        } else {
            System.out.println("Student ID already exists.");
        }
    }


    private void listStudents() {
        printList(studentController.getAllStudents(), "--- Students ---");
    }

    /// //////
    private void updateStudent() {
        int id = promptInt("Student ID: ");
        Student student = findStudentById(id);

        if (student == null) {
            displayNotFoundMessage();
            return;
        }

        updateStudentDetails(student);
        saveUpdatedStudent(student);
    }
    private Student findStudentById(int id) {
        return studentController.getAllStudents().stream()
                .filter(st -> st.getId() == id)
                .findFirst()
                .orElse(null);
    }
    private void updateStudentDetails(Student student) {
        String name = promptString("New name (blank to keep): ");
        String email = promptString("New email (blank to keep): ");

        if (!name.isBlank()) student.setName(name);
        if (!email.isBlank()) student.setEmail(email);
    }
    private void saveUpdatedStudent(Student student) {
        studentController.updateStudent(student);
        System.out.println("Student updated.");
    }

    private void displayNotFoundMessage() {
        System.out.println("Not found.");
    }



    private void deleteStudent() {

        int id = promptInt("Student ID: ");

        System.out.print("Are you sure? (Y/N): ");
        String confirm = scanner.nextLine().trim().toLowerCase();

        if (!confirm.equals("y") && !confirm.equals("yes")) {
            System.out.println("Delete cancelled.");
            return;
        }

        boolean deleted = studentController.deleteStudent(id);

        if (deleted) {
            System.out.println("Student deleted successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }


    private void searchStudentByName() {
        String keyword = promptString("Name keyword: ");
        List<Student> result = studentController.searchStudentsByName(keyword);
        printList(result, "--- Search Results ---");
    }

    private void listActiveStudents() {
        printList(studentController.getActiveStudents(), "--- Active Students ---");
    }

    // ---------------- Helper Input Methods ----------------

    // Simple integer input
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

    // Integer input with min/max validation
    private int promptInt(String msg, int min, int max) {
        while (true) {
            int value = promptInt(msg);
            if (value < min || value > max) {
                System.out.println("Please enter a number between " + min + " and " + max + ".");
                continue;
            }
            return value;
        }
    }

    private String promptString(String msg) {
        System.out.print(msg);
        return scanner.nextLine().trim();
    }

    private <T> void printList(List<T> list, String title) {
        System.out.println(title);
        if (list.isEmpty()) System.out.println("No results found.");
        else list.forEach(System.out::println);
    }
}
