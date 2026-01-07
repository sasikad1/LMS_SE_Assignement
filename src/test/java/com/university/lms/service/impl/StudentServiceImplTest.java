package com.university.lms.service.impl; // එකම පැකේජ් නාමය භාවිතා කරන්න

import com.university.lms.model.Student;
import com.university.lms.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class StudentServiceImplTest {

    private StudentServiceImpl studentService;

    @Test
    void testAddStudent() {
        StudentService service = new StudentServiceImpl();
        Student student = new Student(1, "Kamal", "kamal@cinec.edu");

        service.createStudent(student);

        Student retrieved = service.getStudent(1);
        assertNotNull(retrieved);
//        assertEquals("Kamal", retrieved.getFirstName());
    }
}