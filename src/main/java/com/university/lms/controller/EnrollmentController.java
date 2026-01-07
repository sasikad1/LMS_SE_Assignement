package com.university.lms.controller;

import com.university.lms.model.Enrollment;
import com.university.lms.service.EnrollmentService;

import java.util.List;

public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    public boolean enrollStudent(int studentId, int courseId) {
        return enrollmentService.enrollStudent(studentId, courseId);
    }

    public boolean removeEnrollment(int studentId, int courseId) {
        return enrollmentService.removeEnrollment(studentId, courseId);
    }

    public List<Enrollment> getAllEnrollments() {
        return enrollmentService.getAllEnrollments();
    }

    public List<Enrollment> getEnrollmentsByStudent(int studentId) {
        return enrollmentService.getEnrollmentsByStudent(studentId);
    }

    public List<Enrollment> getEnrollmentsByCourse(int courseId) {
        return enrollmentService.getEnrollmentsByCourse(courseId);
    }
}
