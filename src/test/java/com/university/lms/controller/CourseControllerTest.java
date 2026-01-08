package com.university.lms.controller;

import com.university.lms.model.Course;
import com.university.lms.service.CourseService;
import com.university.lms.service.impl.CourseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CourseControllerTest {

    private CourseController courseController;
    private CourseService courseService;

    @BeforeEach
    void setUp() {
        // Using the actual implementation to verify the full flow
        courseService = new CourseServiceImpl();
        courseController = new CourseController(courseService);

        // Pre-populating a course via the controller
        courseController.addCourse(new Course(101, "Software Engineering", "SE Basics", 3, "Dr. Perera"));
    }

    @Test
    void testAddCourse() {
        Course newCourse = new Course(102, "Database Management", "SQL Intro", 4, "Prof. Silva");
        boolean result = courseController.addCourse(newCourse);

        assertTrue(result, "Controller should successfully pass add request to service.");
        assertEquals(2, courseController.getAllCourses().size());
    }

    @Test
    void testGetCourseById() {
        Course found = courseController.getCourseById(101);
        assertNotNull(found);
        assertEquals("Software Engineering", found.getTitle());
    }

    @Test
    void testUpdateCourse() {
        Course updated = new Course(101, "Advanced Software Engineering", "Advanced SE", 3, "Dr. Perera");
        boolean result = courseController.updateCourse(updated);

        assertTrue(result);
        assertEquals("Advanced Software Engineering", courseController.getCourseById(101).getTitle());
    }

    @Test
    void testDeleteCourse() {
        boolean result = courseController.deleteCourse(101);
        assertTrue(result);
        assertNull(courseController.getCourseById(101));
    }

    @Test
    void testSearchCoursesByInstructor() {
        List<Course> results = courseController.searchCoursesByInstructor("Dr. Perera");
        assertEquals(1, results.size());
        assertEquals("Software Engineering", results.get(0).getTitle());
    }

    @Test
    void testGetAllCourses() {
        List<Course> allCourses = courseController.getAllCourses();
        assertFalse(allCourses.isEmpty());
        assertEquals(1, allCourses.size());
    }
}