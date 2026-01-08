package com.university.lms.view;

import com.university.lms.model.Course;
import com.university.lms.service.CourseService;
import com.university.lms.service.impl.CourseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class CourseViewTest {

    private CourseService courseService;
    private final InputStream systemIn = System.in;

    @BeforeEach
    void setUp() {
        courseService = new CourseServiceImpl();
    }

    private void setMockInput(String data) {
        System.setIn(new ByteArrayInputStream(data.getBytes()));
    }

    @Test
    void testAddCourseFlow() {
        // Sequence:
        // 1 (Choice: Add Course)
        // 101 (Input ID)
        // Java Basics (Input Title)
        // Intro to Java (Input Description)
        // 3 (Input Credits)
        // Dr. Smith (Input Instructor)
        // 0 (Choice: Back to Exit)
        String inputData = "1\n101\nJava Basics\nIntro to Java\n3\nDr. Smith\n0\n";
        setMockInput(inputData);

        Scanner scanner = new Scanner(System.in);
        CourseView courseView = new CourseView(scanner, courseService);

        courseView.start();

        Course saved = courseService.getCourse(101);
        assertNotNull(saved, "Course should be saved in the service");
        assertEquals("Java Basics", saved.getTitle());

        System.setIn(systemIn); // Restore System.in
    }

    @Test
    void testListCoursesFlow() {
        // Pre-populate data
        courseService.createCourse(new Course(201, "Python", "Learn Python", 4, "Prof. Snake"));

        // Sequence:
        // 2 (Choice: List Courses)
        // 0 (Choice: Back to Exit)
        String inputData = "2\n0\n";
        setMockInput(inputData);

        Scanner scanner = new Scanner(System.in);
        CourseView courseView = new CourseView(scanner, courseService);

        assertDoesNotThrow(() -> courseView.start());

        System.setIn(systemIn);
    }

    @Test
    void testDeleteCourseFlow() {
        // Pre-populate data
        courseService.createCourse(new Course(301, "C#", "Learn C#", 2, "Mr. Sharp"));

        // Sequence:
        // 4 (Choice: Delete Course)
        // 301 (Input ID to delete)
        // 0 (Choice: Back to Exit)
        String inputData = "4\n301\n0\n";
        setMockInput(inputData);

        Scanner scanner = new Scanner(System.in);
        CourseView courseView = new CourseView(scanner, courseService);

        courseView.start();

        assertNull(courseService.getCourse(301), "Course should be removed from service");

        System.setIn(systemIn);
    }
}