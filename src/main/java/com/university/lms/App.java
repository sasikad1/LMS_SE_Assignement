package com.university.lms;

import com.university.lms.view.ConsoleView;

public class App {
    public static void main(String[] args) {
        System.out.println("=== University Learning Management System ===");

        // Dependency chain starts here
        ConsoleView consoleView = new ConsoleView();
        consoleView.start();
    }
}