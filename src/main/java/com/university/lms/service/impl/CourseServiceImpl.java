package com.university.lms.service.impl;

import com.university.lms.model.Course;
import com.university.lms.service.CourseService;

import java.util.*;
import java.util.stream.Collectors;

public class CourseServiceImpl implements CourseService {

    private final Map<Integer, Course> coursesMap = new HashMap<>();

    @Override
    public boolean createCourse(Course course) {
        if (coursesMap.containsKey(course.getId())) return false;
        coursesMap.put(course.getId(), course);
        return true;
    }

    @Override
    public Course getCourse(int id) {
        return coursesMap.get(id);
    }

    @Override
    public boolean updateCourse(Course course) {
        if (!coursesMap.containsKey(course.getId())) return false;
        coursesMap.put(course.getId(), course);
        return true;
    }

    @Override
    public boolean deleteCourse(int id) {
        return coursesMap.remove(id) != null;
    }

    @Override
    public List<Course> getAllCourses() {
        return new ArrayList<>(coursesMap.values());
    }

    @Override
    public List<Course> getCoursesByInstructor(String instructor) {
        return coursesMap.values().stream()
                .filter(c -> c.getInstructor().equalsIgnoreCase(instructor))
                .collect(Collectors.toList());
    }
}
