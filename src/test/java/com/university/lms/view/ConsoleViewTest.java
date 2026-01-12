package com.university.lms.view;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ConsoleViewTest {

    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void setUp() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    void tearDown() {
        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    @Test
    @DisplayName("Should display menu and handle invalid inputs in promptInt")
    void testMenuAndPromptIntLogic() {
        String simulatedInput = "invalid\n9\n0\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        ConsoleView consoleView = new ConsoleView();
        consoleView.start();

        String capturedOutput = outputStream.toString();

        assertTrue(capturedOutput.contains("=== Main Menu ==="));
        assertTrue(capturedOutput.contains("1. Student Management"));

        assertTrue(capturedOutput.contains("Invalid number."));

        assertTrue(capturedOutput.contains("Enter between 0 and 3."));

        assertTrue(capturedOutput.contains("Goodbye!"));
    }
}