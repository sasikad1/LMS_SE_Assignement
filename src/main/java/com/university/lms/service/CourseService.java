package com.university.lms.service;

import com.university.lms.model.Course;

import java.util.List;

public interface CourseService {
    boolean createCourse(Course course);
    Course getCourse(int id);
    boolean updateCourse(Course course);
    boolean deleteCourse(int id);
    List<Course> getAllCourses();
    List<Course> getCoursesByInstructor(String instructor);
}
