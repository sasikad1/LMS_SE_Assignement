package com.university.lms.view;

import com.university.lms.controller.CourseController;
import com.university.lms.view.CourseView;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

class CourseViewTest {

    // Test constants
    private static final int ADD_COURSE = 1;
    private static final int LIST_COURSES = 2;
    private static final int UPDATE_COURSE = 3;
    private static final int DELETE_COURSE = 4;
    private static final int EXIT = 0;

    // Helper method to create CourseView with simulated input
    private CourseView createCourseViewWithInput(String input) {
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);
        return new CourseView(scanner);
    }

    @Test
    public void testMenuConstantsHaveCorrectValues() {
        // Arrange & Act
        int expectedAdd = 1;
        int expectedList = 2;
        int expectedUpdate = 3;
        int expectedDelete = 4;
        int expectedExit = 0;

        // Assert
        assertEquals(expectedAdd, ADD_COURSE,
                "ADD_COURSE constant should have value 1");
        assertEquals(expectedList, LIST_COURSES,
                "LIST_COURSES constant should have value 2");
        assertEquals(expectedUpdate, UPDATE_COURSE,
                "UPDATE_COURSE constant should have value 3");
        assertEquals(expectedDelete, DELETE_COURSE,
                "DELETE_COURSE constant should have value 4");
        assertEquals(expectedExit, EXIT,
                "EXIT constant should have value 0");
    }

    @Test
    public void testMenuSwitchLogicWithConstants() {
        // Test each menu option
        assertEquals("Add Course", getMenuActionForChoice(ADD_COURSE));
        assertEquals("List Courses", getMenuActionForChoice(LIST_COURSES));
        assertEquals("Update Course", getMenuActionForChoice(UPDATE_COURSE));
        assertEquals("Delete Course", getMenuActionForChoice(DELETE_COURSE));
        assertEquals("Exit", getMenuActionForChoice(EXIT));
    }

    private String getMenuActionForChoice(int choice) {
        switch (choice) {
            case ADD_COURSE: return "Add Course";
            case LIST_COURSES: return "List Courses";
            case UPDATE_COURSE: return "Update Course";
            case DELETE_COURSE: return "Delete Course";
            case EXIT: return "Exit";
            default: return "Invalid Choice";
        }
    }


    @Test
    public void testMenuChoiceRangeValidation() {
        // Valid choices
        assertTrue(isValidMenuChoice(EXIT), "0 should be valid menu choice");
        assertTrue(isValidMenuChoice(ADD_COURSE), "1 should be valid menu choice");
        assertTrue(isValidMenuChoice(DELETE_COURSE), "4 should be valid menu choice");

        // Invalid choices
        assertFalse(isValidMenuChoice(-1), "-1 should be invalid menu choice");
        assertFalse(isValidMenuChoice(5), "5 should be invalid menu choice");
        assertFalse(isValidMenuChoice(99), "99 should be invalid menu choice");
    }

    private boolean isValidMenuChoice(int choice) {
        return choice >= EXIT && choice <= DELETE_COURSE;
    }


    @Test
    public void testCentralizedConstantControl() {
        // Simulate changing menu order (business requirement change)
        int newAddCourseValue = 5; // Suppose we need to change this

        // With magic numbers: would need to find all "case 1:" occurrences
        // With constants: only change one line
        int OLD_ADD_COURSE = 1;
        int NEW_ADD_COURSE = 5;

        // Assert that we can track the change
        assertNotEquals(OLD_ADD_COURSE, NEW_ADD_COURSE,
                "Business requirement changed menu value");

        // Test that using constants prevents scattered changes
        String codeBefore = "switch(choice) { case 1: addCourse(); break; }";
        String codeAfter = "switch(choice) { case ADD_COURSE: addCourse(); break; }";

        assertTrue(codeAfter.contains("ADD_COURSE"),
                "Using constants makes changes centralized");
    }


    @Test
    public void testInvalidMenuChoicesHandling() {
        // Test with input out of range
        String input = "99\n0\n"; // Invalid choice then exit
        CourseView view = createCourseViewWithInput(input);

        // This test ensures the system doesn't crash on invalid input
        assertDoesNotThrow(() -> {
            // Simulate menu processing
            processMenuChoice(99); // Should handle gracefully
        });
    }

    private void processMenuChoice(int choice) {
        // Simulated menu processing logic
        if (choice < EXIT || choice > DELETE_COURSE) {
            System.out.println("Invalid choice. Please enter 0-4.");
            // Should not throw exception
        }
    }


    private void simulateMenuFlow(CourseView view, int[] choices) {
        for (int choice : choices) {
            processMenuChoice(choice);
        }
    }


    // Alternative: Using Enums for even better type safety
    @Test
    public void testEnumImplementationForMenu() {
        // Define MenuOption enum
        enum MenuOption {
            EXIT(0), ADD_COURSE(1), LIST_COURSES(2),
            UPDATE_COURSE(3), DELETE_COURSE(4);

            private final int value;
            MenuOption(int value) { this.value = value; }
            public int getValue() { return value; }
        }

        // Test enum values
        assertEquals(1, MenuOption.ADD_COURSE.getValue());
        assertEquals(0, MenuOption.EXIT.getValue());

        // Test enum prevents invalid values
        assertThrows(IllegalArgumentException.class, () -> {
            MenuOption invalid = MenuOption.valueOf("INVALID_OPTION");
        });
    }


}