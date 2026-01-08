package com.university.lms.service.impl;

import com.university.lms.model.Course;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class CourseServiceImplTest {

    private CourseServiceImpl courseService;

    @BeforeEach
    void setUp() {
        courseService = new CourseServiceImpl();
        // Pre-populating a course for testing
        courseService.createCourse(new Course(101, "Java Programming", "Learn Java", 3, "Dr. Smith"));
    }

    @Test
    void testCreateCourseSuccess() {
        Course newCourse = new Course(102, "Database Systems", "SQL Basics", 4, "Prof. Miller");
        boolean result = courseService.createCourse(newCourse);

        assertTrue(result, "New course should be created successfully.");
        assertEquals(2, courseService.getAllCourses().size());
    }

    @Test
    void testCreateDuplicateCourseFail() {
        // Attempting to add a course with existing ID (101)
        Course duplicate = new Course(101, "Advanced Java", "Concurrency", 3, "Dr. Smith");
        boolean result = courseService.createCourse(duplicate);

        assertFalse(result, "Should fail when adding a course with a duplicate ID.");
    }

    @Test
    void testGetCourseById() {
        Course found = courseService.getCourse(101);
        assertNotNull(found);
        assertEquals("Java Programming", found.getTitle());
    }

    @Test
    void testUpdateCourse() {
        Course updatedCourse = new Course(101, "Java Mastery", "Advanced Topics", 4, "Dr. Smith");
        boolean result = courseService.updateCourse(updatedCourse);

        assertTrue(result);
        assertEquals("Java Mastery", courseService.getCourse(101).getTitle());
    }

    @Test
    void testDeleteCourse() {
        boolean deleted = courseService.deleteCourse(101);
        assertTrue(deleted);
        assertNull(courseService.getCourse(101));
    }

    @Test
    void testGetCoursesByInstructor() {
        // Adding another course for the same instructor
        courseService.createCourse(new Course(103, "C++ Basics", "Intro to CPP", 3, "Dr. Smith"));

        // Testing the Stream filter logic
        List<Course> results = courseService.getCoursesByInstructor("Dr. Smith");
        assertEquals(2, results.size(), "Should find 2 courses for Dr. Smith");
        assertTrue(results.stream().allMatch(c -> c.getInstructor().equals("Dr. Smith")));
    }
}