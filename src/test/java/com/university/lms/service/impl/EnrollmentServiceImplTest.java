package com.university.lms.service.impl;

import com.university.lms.model.Course;
import com.university.lms.model.Student;
import com.university.lms.service.CourseService;
import com.university.lms.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnrollmentServiceImplTest {

    private EnrollmentServiceImpl enrollmentService;
    private StudentService studentService;
    private CourseService courseService;

    @BeforeEach
    void setUp() {
        // Dependency Injection manually for testing
        studentService = new StudentServiceImpl();
        courseService = new CourseServiceImpl();
        enrollmentService = new EnrollmentServiceImpl(studentService, courseService);

        // Pre-populating data needed for enrollment tests
        studentService.createStudent(new Student(1, "Test Student", "test@univ.edu"));
        courseService.createCourse(new Course(101, "Java Programming", "Basics of Java", 3, "Dr. Smith"));
    }

    @Test
    void testSuccessfulEnrollment() {
        // Test case: Student (1) exists and Course (101) exists
        boolean result = enrollmentService.enrollStudent(1, 101);

        assertTrue(result, "Enrollment should be successful for valid student and course IDs.");
        assertEquals(1, enrollmentService.getAllEnrollments().size());
    }

    @Test
    void testEnrollmentFailsForInvalidStudent() {
        // Test case: Course exists but Student (999) does not exist
        boolean result = enrollmentService.enrollStudent(999, 101);

        assertFalse(result, "Enrollment should fail if the student does not exist.");
    }

    @Test
    void testEnrollmentFailsForInvalidCourse() {
        // Test case: Student exists but Course (555) does not exist
        boolean result = enrollmentService.enrollStudent(1, 555);

        assertFalse(result, "Enrollment should fail if the course does not exist.");
    }

    @Test
    void testDuplicateEnrollmentPrevention() {
        // Test case: Enrolling the same student in the same course twice
        enrollmentService.enrollStudent(1, 101);
        boolean secondResult = enrollmentService.enrollStudent(1, 101);

        assertFalse(secondResult, "System should prevent enrolling a student in the same course twice.");
    }

    @Test
    void testRemoveEnrollment() {
        // Test case: Adding and then removing an enrollment
        enrollmentService.enrollStudent(1, 101);
        boolean removed = enrollmentService.removeEnrollment(1, 101);

        assertTrue(removed, "Enrollment should be successfully removed.");
        assertTrue(enrollmentService.getAllEnrollments().isEmpty());
    }
}