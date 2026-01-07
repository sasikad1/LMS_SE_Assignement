package com.university.lms.service;

import com.university.lms.model.Student;

import java.util.List;

public interface StudentService {

    boolean createStudent(Student student);

    Student getStudent(int id);

    boolean updateStudent(Student student);

    boolean deleteStudent(int id);

    List<Student> getAllStudents();

    List<Student> getActiveStudents();
}
