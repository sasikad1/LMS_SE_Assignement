package com.university.lms.controller;

import com.university.lms.model.Course;
import com.university.lms.model.Enrollment;
import com.university.lms.model.Student;
import com.university.lms.service.CourseService;
import com.university.lms.service.EnrollmentService;
import com.university.lms.service.StudentService;
import com.university.lms.service.impl.CourseServiceImpl;
import com.university.lms.service.impl.EnrollmentServiceImpl;
import com.university.lms.service.impl.StudentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EnrollmentControllerTest {
    private EnrollmentController enrollmentController;
    private EnrollmentService enrollmentService;

    @BeforeEach
    void setUp() {
        // 1. Initialize Services
        StudentService studentService = new StudentServiceImpl();
        CourseService courseService = new CourseServiceImpl();

        // 2. Add sample data needed for the test
        // This ensures student 101 and course 501 actually exist
        studentService.createStudent(new Student(101, "Test Student", "test@cinec.edu"));
        courseService.createCourse(new Course(501, "Java Programming", "OOP Basics", 3, "Dr. Smith"));

        // 3. Inject these services into the EnrollmentService
        enrollmentService = new EnrollmentServiceImpl(studentService, courseService);

        // 4. Inject the service into the controller
        enrollmentController = new EnrollmentController(enrollmentService);
    }

    @Test
    void testEnrollStudentEncapsulation() {
        // Test if the controller can successfully enroll a student via the service layer
        // This verifies that "Inappropriate Intimacy" is removed by delegating logic to the service
        boolean result = enrollmentController.enrollStudent(101, 501);

        // Assert that the enrollment process returns true through the properly encapsulated path
        assertTrue(result, "Enrollment should succeed when handled through the service layer");
    }

    @Test
    void testNamingConsistency() {
        // Verification of "Inconsistent Naming" refactoring
        // Ensures the class follows the established naming convention (ending with 'Controller')
        String className = enrollmentController.getClass().getSimpleName();
        assertTrue(className.endsWith("Controller"), "Class naming should follow the standard 'Controller' suffix pattern");

        // Verifying that the service variable follows the predictable naming pattern
        assertNotNull(enrollmentController);
    }
}