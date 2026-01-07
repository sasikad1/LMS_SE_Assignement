package com.university.lms.controller;

import com.university.lms.model.Course;
import com.university.lms.service.CourseService;

import java.util.List;

public class CourseController {

    private final CourseService courseService;

    // Constructor injection
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    public boolean addCourse(Course course) {
        return courseService.createCourse(course);
    }

    public Course getCourseById(int courseId) {
        return courseService.getCourse(courseId);
    }

    public boolean updateCourse(Course course) {
        return courseService.updateCourse(course);
    }

    public boolean deleteCourse(int courseId) {
        return courseService.deleteCourse(courseId);
    }

    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    public List<Course> searchCoursesByInstructor(String instructor) {
        return courseService.getCoursesByInstructor(instructor);
    }
}