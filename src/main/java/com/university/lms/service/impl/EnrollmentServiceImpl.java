package com.university.lms.service.impl;

import com.university.lms.model.Enrollment;
import com.university.lms.service.EnrollmentService;
import com.university.lms.service.StudentService;
import com.university.lms.service.CourseService;

import java.util.ArrayList;
import java.util.List;

public class EnrollmentServiceImpl implements EnrollmentService {

    private final List<Enrollment> enrollments = new ArrayList<>();
    private final StudentService studentService;
    private final CourseService courseService;

    public EnrollmentServiceImpl(StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }

    @Override
    public boolean enrollStudent(int studentId, int courseId) {
        // Validate using injected services
        if (studentService.getStudent(studentId) == null) {
            System.out.println("Student ID " + studentId + " not found.");
            return false;
        }

        if (courseService.getCourse(courseId) == null) {
            System.out.println("Course ID " + courseId + " not found.");
            return false;
        }

        Enrollment newEnrollment = new Enrollment(studentId, courseId);
        if (enrollments.contains(newEnrollment)) {
            System.out.println("Student is already enrolled in this course.");
            return false;
        }

        enrollments.add(newEnrollment);
        System.out.println("Student " + studentId + " enrolled in Course " + courseId + " successfully.");
        return true;
    }

    @Override
    public boolean removeEnrollment(int studentId, int courseId) {
        Enrollment enrollment = new Enrollment(studentId, courseId);
        boolean removed = enrollments.remove(enrollment);

        if (removed) {
            System.out.println("Enrollment removed successfully.");
        } else {
            System.out.println("Enrollment not found.");
        }

        return removed;
    }

    @Override
    public List<Enrollment> getAllEnrollments() {
        return new ArrayList<>(enrollments);
    }

    @Override
    public List<Enrollment> getEnrollmentsByStudent(int studentId) {
        return enrollments.stream()
                .filter(e -> e.getStudentId() == studentId)
                .toList();
    }

    @Override
    public List<Enrollment> getEnrollmentsByCourse(int courseId) {
        return enrollments.stream()
                .filter(e -> e.getCourseId() == courseId)
                .toList();
    }
}