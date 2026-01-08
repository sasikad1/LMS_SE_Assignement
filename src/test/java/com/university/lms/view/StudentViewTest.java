package com.university.lms.view;

import com.university.lms.model.Student;
import com.university.lms.service.StudentService;
import com.university.lms.service.impl.StudentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class StudentViewTest {

    private StudentService studentService;
    private final InputStream systemIn = System.in; // Backup original System.in

    @BeforeEach
    void setUp() {
        studentService = new StudentServiceImpl();
    }

    /**
     * Helper method to simulate console input
     */
    private void setMockInput(String data) {
        System.setIn(new ByteArrayInputStream(data.getBytes()));
    }

    @Test
    void testAddStudentFlow() {
        // Sequence:
        // 1 (Choice: Add Student)
        // 101 (Input ID)
        // John Doe (Input Name)
        // john@email.com (Input Email)
        // 0 (Choice: Back to Exit)
        String inputData = "1\n101\nJohn Doe\njohn@email.com\n0\n";
        setMockInput(inputData);

        Scanner scanner = new Scanner(System.in);
        StudentView studentView = new StudentView(scanner, studentService);

        // Run the view loop
        studentView.start();

        // Verification
        Student saved = studentService.getStudent(101);
        assertNotNull(saved, "Student should be saved in the service");
        assertEquals("John Doe", saved.getName());

        // Restore System.in
        System.setIn(systemIn);
    }

    @Test
    void testSearchStudentFlow() {
        // Pre-populate data
        studentService.createStudent(new Student(202, "Alice Smith", "alice@email.com"));

        // Sequence:
        // 5 (Choice: Search by Name)
        // Alice (Input Keyword)
        // 0 (Choice: Back to Exit)
        String inputData = "5\nAlice\n0\n";
        setMockInput(inputData);

        Scanner scanner = new Scanner(System.in);
        StudentView studentView = new StudentView(scanner, studentService);

        // Run the view loop
        assertDoesNotThrow(() -> studentView.start());

        // Restore System.in
        System.setIn(systemIn);
    }

    @Test
    void testListActiveStudentsFlow() {
        // Sequence:
        // 6 (Choice: List Active)
        // 0 (Choice: Back to Exit)
        String inputData = "6\n0\n";
        setMockInput(inputData);

        Scanner scanner = new Scanner(System.in);
        StudentView studentView = new StudentView(scanner, studentService);

        assertDoesNotThrow(() -> studentView.start());

        // Restore System.in
        System.setIn(systemIn);
    }
}