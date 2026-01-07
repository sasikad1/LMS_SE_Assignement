package com.university.lms.service;

import com.university.lms.model.Enrollment;

import java.util.List;

public interface EnrollmentService {
    boolean enrollStudent(int studentId, int courseId);
    boolean removeEnrollment(int studentId, int courseId);
    List<Enrollment> getAllEnrollments();
    List<Enrollment> getEnrollmentsByStudent(int studentId);
    List<Enrollment> getEnrollmentsByCourse(int courseId);
}