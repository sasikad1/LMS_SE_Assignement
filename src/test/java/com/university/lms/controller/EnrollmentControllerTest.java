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

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EnrollmentControllerTest {

    private EnrollmentController enrollmentController;
    private EnrollmentService enrollmentService;
    private StudentService studentService;
    private CourseService courseService;

    @BeforeEach
    void setUp() {
        // Setup underlying services
        studentService = new StudentServiceImpl();
        courseService = new CourseServiceImpl();
        enrollmentService = new EnrollmentServiceImpl(studentService, courseService);

        // Initialize the controller
        enrollmentController = new EnrollmentController(enrollmentService);

        // Pre-populate data required for enrollment
        studentService.addStudent(new Student(1, "John Doe", "john@university.com"));
        courseService.createCourse(new Course(101, "Algorithms", "Advanced Algorithms", 4, "Dr. Turing"));
    }

    @Test
    void testEnrollStudentSuccess() {
        boolean result = enrollmentController.enrollStudent(1, 101);

        assertTrue(result, "Controller should return success for valid student and course IDs.");
        assertEquals(1, enrollmentController.getAllEnrollments().size());
    }

    @Test
    void testEnrollStudentFail() {
        // Enrolling a student that doesn't exist
        boolean result = enrollmentController.enrollStudent(999, 101);

        assertFalse(result, "Controller should return false if enrollment fails in the service layer.");
    }

    @Test
    void testRemoveEnrollment() {
        enrollmentController.enrollStudent(1, 101);
        boolean result = enrollmentController.removeEnrollment(1, 101);

        assertTrue(result);
        assertEquals(0, enrollmentController.getAllEnrollments().size());
    }

    @Test
    void testGetEnrollmentsByStudent() {
        enrollmentController.enrollStudent(1, 101);
        List<Enrollment> enrollments = enrollmentController.getEnrollmentsByStudent(1);

        assertEquals(1, enrollments.size());
        assertEquals(101, enrollments.get(0).getCourseId());
    }

    @Test
    void testGetEnrollmentsByCourse() {
        enrollmentController.enrollStudent(1, 101);
        List<Enrollment> enrollments = enrollmentController.getEnrollmentsByCourse(101);

        assertEquals(1, enrollments.size());
        assertEquals(1, enrollments.get(0).getStudentId());
    }
}