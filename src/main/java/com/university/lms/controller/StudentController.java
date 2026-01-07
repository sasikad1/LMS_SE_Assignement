package com.university.lms.controller;

import com.university.lms.model.Student;
import com.university.lms.service.StudentService;

import java.util.List;

public class StudentController {

    private final StudentService studentService;

    // Constructor injection - StudentService dependency injected
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    public boolean addStudent(Student student) {
        return studentService.createStudent(student);
    }

    public Student getStudentById(int id) {
        return studentService.getStudent(id);
    }

    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    public boolean updateStudent(Student student) {
        return studentService.updateStudent(student);
    }

    public boolean deleteStudent(int id) {
        return studentService.deleteStudent(id);
    }

    public List<Student> searchStudentsByName(String keyword) {
        return studentService.getAllStudents().stream()
                .filter(s -> s.getName().toLowerCase().contains(keyword.toLowerCase()))
                .toList();
    }

    public List<Student> getActiveStudents() {
        return studentService.getActiveStudents();
    }
}