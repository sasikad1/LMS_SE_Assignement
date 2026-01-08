package com.university.lms.view;

import com.university.lms.model.Course;
import com.university.lms.model.Student;
import com.university.lms.service.CourseService;
import com.university.lms.service.EnrollmentService;
import com.university.lms.service.StudentService;
import com.university.lms.service.impl.CourseServiceImpl;
import com.university.lms.service.impl.EnrollmentServiceImpl;
import com.university.lms.service.impl.StudentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class EnrollmentViewTest {

    private EnrollmentService enrollmentService;
    private StudentService studentService;
    private CourseService courseService;
    private final InputStream systemIn = System.in;

    @BeforeEach
    void setUp() {
        // Initialize real services to verify actual data flow
        studentService = new StudentServiceImpl();
        courseService = new CourseServiceImpl();
        enrollmentService = new EnrollmentServiceImpl(studentService, courseService);

        // Pre-populate data so the menu has something to show and enroll
        studentService.createStudent(new Student(1, "Saman Kumara", "saman@cinec.edu"));
        courseService.createCourse(new Course(101, "Java Basics", "Intro", 3, "Dr. Smith"));
    }

    private void setMockInput(String data) {
        System.setIn(new ByteArrayInputStream(data.getBytes()));
    }

    @Test
    void testEnrollStudentFlow() {
        // Sequence:
        // 1 (Choice: Enroll Student)
        // 1 (Input Student ID)
        // 101 (Input Course ID)
        // 0 (Choice: Back to Exit)
        String inputData = "1\n1\n101\n0\n";
        setMockInput(inputData);

        Scanner scanner = new Scanner(System.in);
        EnrollmentView enrollmentView = new EnrollmentView(scanner, enrollmentService, studentService, courseService);

        // Run view logic
        enrollmentView.start();

        // Verify enrollment was actually recorded in the service layer
        assertFalse(enrollmentService.getAllEnrollments().isEmpty(), "Enrollment list should not be empty");
        assertEquals(1, enrollmentService.getAllEnrollments().get(0).getStudentId());
        assertEquals(101, enrollmentService.getAllEnrollments().get(0).getCourseId());

        System.setIn(systemIn); // Restore System.in
    }

    @Test
    void testRemoveEnrollmentFlow() {
        // Pre-enroll a student manually
        enrollmentService.enrollStudent(1, 101);

        // Sequence:
        // 3 (Choice: Remove Enrollment)
        // 1 (Input Student ID)
        // 101 (Input Course ID)
        // 0 (Choice: Back to Exit)
        String inputData = "3\n1\n101\n0\n";
        setMockInput(inputData);

        Scanner scanner = new Scanner(System.in);
        EnrollmentView enrollmentView = new EnrollmentView(scanner, enrollmentService, studentService, courseService);

        enrollmentView.start();

        // Verify removal
        assertTrue(enrollmentService.getAllEnrollments().isEmpty(), "Enrollment list should be empty after removal");

        System.setIn(systemIn);
    }

    @Test
    void testListEnrollmentsFlow() {
        // Sequence: 2 (List), then 0 (Exit)
        String inputData = "2\n0\n";
        setMockInput(inputData);

        Scanner scanner = new Scanner(System.in);
        EnrollmentView enrollmentView = new EnrollmentView(scanner, enrollmentService, studentService, courseService);

        assertDoesNotThrow(() -> enrollmentView.start());

        System.setIn(systemIn);
    }
}