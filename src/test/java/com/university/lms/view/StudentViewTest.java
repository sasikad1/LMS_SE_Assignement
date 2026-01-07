package com.university.lms.view;

import com.university.lms.model.Student;
import com.university.lms.service.impl.StudentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;

class StudentViewTest {
    private StudentView studentView;
    private Student testStudent;

    @BeforeEach
    void setUp() {
        studentView = new StudentView(new Scanner(System.in), new StudentServiceImpl());
        testStudent = new Student(101, "Original Name", "old@email.com");
    }



    @Test
    void testUpdateLogicFlow() {

        String newName = "Updated Name";
        String newEmail = "new@email.com";

        testStudent.setName(newName);
        testStudent.setEmail(newEmail);

        assertEquals("Updated Name", testStudent.getName());
        assertEquals("new@email.com", testStudent.getEmail());
    }
}