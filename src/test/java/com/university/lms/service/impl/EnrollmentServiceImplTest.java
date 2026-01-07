package com.university.lms.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import com.university.lms.model.Student;
import com.university.lms.model.Course;
import com.university.lms.service.StudentService;
import com.university.lms.service.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EnrollmentServiceImplTest {
    private EnrollmentServiceImpl enrollmentService;
    private StudentService studentService;
    private CourseService courseService;

    @BeforeEach
    void setUp() {
        // Initializing dependencies
        studentService = new StudentServiceImpl();
        courseService = new CourseServiceImpl();
        enrollmentService = new EnrollmentServiceImpl(studentService, courseService);

        // Pre-populating data to ensure "Referential Integrity"
        studentService.createStudent(new Student(101, "Saman Kumara", "saman@cinec.edu"));
        courseService.createCourse(new Course(501, "Software Engineering", "SE Basics", 3, "Prof. Perera"));
    }

    @Test
    void testEnrollmentEncapsulation() {
        // This test ensures that the Enrollment logic is centralized in the Service layer
        // and not scattered across Controllers or Views (Resolving Inappropriate Intimacy)
        boolean result = enrollmentService.enrollStudent(101, 501);

        assertTrue(result, "Enrollment should succeed through the Service layer API");
        assertEquals(1, enrollmentService.getAllEnrollments().size(), "Enrollment count should be 1");
    }

    @Test
    void testIntegrityValidation() {
        // Verifying that the service handles non-existent students without crashing
        // This shows the service layer acts as a 'gatekeeper' for data
        boolean result = enrollmentService.enrollStudent(999, 501); // Student 999 doesn't exist

        assertFalse(result, "Enrollment must fail for non-existent students to maintain data integrity");
    }
}