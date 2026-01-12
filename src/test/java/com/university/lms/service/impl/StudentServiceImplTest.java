package com.university.lms.service.impl;

import com.university.lms.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class StudentServiceImplTest {

    private StudentServiceImpl studentService;

    @BeforeEach
    void setUp() {
        studentService = new StudentServiceImpl();
        // Pre-populating data for testing
        studentService.addStudent(new Student(1, "Saman Kumara", "saman@cinec.edu"));
        studentService.addStudent(new Student(2, "Amara Siri", "amara@cinec.edu"));
    }

    @Test
    void testAddStudentSuccess() {
        Student newStudent = new Student(3, "Nimmi Perera", "nimmi@cinec.edu");
        boolean result = studentService.addStudent(newStudent);

        assertTrue(result, "New student should be added successfully.");
        assertEquals(3, studentService.getAllStudents().size());
    }

    @Test
    void testPreventDuplicateId() {
        // Attempting to add a student with an existing ID (ID: 1)
        Student duplicateStudent = new Student(1, "New Name", "new@cinec.edu");
        boolean result = studentService.addStudent(duplicateStudent);

        assertFalse(result, "System must prevent adding two students with the same ID.");
    }

    @Test
    void testGetStudentById() {
        Student found = studentService.getStudent(1);
        assertNotNull(found);
        assertEquals("Saman Kumara", found.getName());
    }

    @Test
    void testSearchStudentsByName() {
        // Verifying that the Stream Filter logic works correctly
        List<Student> results = studentService.searchStudentsByName("Saman");
        assertEquals(1, results.size());
        assertEquals("Saman Kumara", results.get(0).getName());
    }

    @Test
    void testGetSortedStudents() {
        // Verifying the Comparator logic (Sorted order: Amara, Saman)
        List<Student> sortedList = studentService.getSortedStudents();
        assertEquals("Amara Siri", sortedList.get(0).getName());
        assertEquals("Saman Kumara", sortedList.get(1).getName());
    }

    @Test
    void testDeleteStudent() {
        boolean removed = studentService.deleteStudent(1);
        assertTrue(removed);
        assertNull(studentService.getStudent(1));
    }
}