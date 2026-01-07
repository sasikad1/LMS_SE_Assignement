package com.university.lms.service;

import com.university.lms.model.Enrollment;

import java.util.List;

public interface EnrollmentService {

    /**
     * Enroll a student into a course
     * @return true if enrollment succeeded, false if already exists
     */
    boolean enrollStudent(int studentId, int courseId);

    /**
     * Remove a student's enrollment
     * @return true if removed, false if not found
     */
    boolean removeEnrollment(int studentId, int courseId);

    List<Enrollment> getAllEnrollments();

    List<Enrollment> getEnrollmentsByStudent(int studentId); // Add this
    List<Enrollment> getEnrollmentsByCourse(int courseId);   // Add this
}
