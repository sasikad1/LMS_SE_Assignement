package com.university.lms.service.impl;

import com.university.lms.model.Student;
import com.university.lms.service.StudentService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StudentServiceImpl implements StudentService {

    private final List<Student> students = new ArrayList<>();

    // CREATE
    @Override
    public boolean createStudent(Student student) {

        boolean exists = students.stream()
                .anyMatch(s -> s.getId() == student.getId());

        if (exists) {
            return false; // Duplicate ID
        }

        students.add(student);
        return true;
    }

    // READ
    @Override
    public Student getStudent(int id) {
        return students.stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // UPDATE
    @Override
    public boolean updateStudent(Student updatedStudent) {
        Student existing = getStudent(updatedStudent.getId());
        if (existing == null) {
            return false;
        }

        existing.setName(updatedStudent.getName());
        existing.setEmail(updatedStudent.getEmail());
        existing.setActive(updatedStudent.isActive());

        return true;
    }

    // DELETE
    @Override
    public boolean deleteStudent(int id) {
        return students.removeIf(s -> s.getId() == id);
    }

    // READ ALL
    @Override
    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }

    // FILTER
    @Override
    public List<Student> getActiveStudents() {
        return students.stream()
                .filter(Student::isActive)
                .toList();
    }

    public List<Student> searchStudentsByName(String name) {
        return students.stream()
                .filter(s -> s.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Student> getSortedStudents() {
        return students.stream()
                .sorted(Comparator.comparing(Student::getName))
                .collect(Collectors.toList());
    }
}
