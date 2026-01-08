package com.university.lms.controller;

import com.university.lms.model.Student;
import com.university.lms.service.StudentService;
import com.university.lms.service.impl.StudentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentControllerTest {

    private StudentController studentController;
    private StudentService studentService;

    @BeforeEach
    void setUp() {
        // Initialize the actual service and inject it into the controller
        studentService = new StudentServiceImpl();
        studentController = new StudentController(studentService);

        // Pre-populate with sample data
        studentController.addStudent(new Student(1, "Alice Johnson", "alice@univ.edu"));
        studentController.addStudent(new Student(2, "Bob Smith", "bob@univ.edu"));
    }

    @Test
    void testAddStudent() {
        Student newStudent = new Student(3, "Charlie Brown", "charlie@univ.edu");
        boolean result = studentController.addStudent(newStudent);

        assertTrue(result);
        assertEquals(3, studentController.getAllStudents().size());
    }

    @Test
    void testGetStudentById() {
        Student found = studentController.getStudentById(1);
        assertNotNull(found);
        assertEquals("Alice Johnson", found.getName());
    }

    @Test
    void testUpdateStudent() {
        Student updated = new Student(1, "Alice J. Smith", "alice_new@univ.edu");
        boolean result = studentController.updateStudent(updated);

        assertTrue(result);
        assertEquals("Alice J. Smith", studentController.getStudentById(1).getName());
    }

    @Test
    void testDeleteStudent() {
        boolean result = studentController.deleteStudent(2);
        assertTrue(result);
        assertNull(studentController.getStudentById(2));
    }

    @Test
    void testSearchStudentsByName() {
        // Testing the keyword search logic (case-insensitive)
        List<Student> results = studentController.searchStudentsByName("alice");
        assertEquals(1, results.size());
        assertTrue(results.get(0).getName().contains("Alice"));
    }

    @Test
    void testGetActiveStudents() {
        List<Student> active = studentController.getActiveStudents();
        assertFalse(active.isEmpty());
        assertTrue(active.get(0).isActive());
    }

    @Test
    void testGetSortedStudentList() {
        List<Student> sorted = studentController.getSortedStudentList();
        // Alice should come before Bob alphabetically
        assertEquals("Alice Johnson", sorted.get(0).getName());
        assertEquals("Bob Smith", sorted.get(1).getName());
    }
}