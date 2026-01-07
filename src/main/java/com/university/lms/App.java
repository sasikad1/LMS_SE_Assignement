package com.university.lms;

import com.university.lms.view.ConsoleView;

public class App {
    public static void main(String[] args) {
        System.out.println("=== University Learning Management System ===");
        System.out.println("Version 1.0");
        System.out.println("=============================================\n");

        ConsoleView consoleView = new ConsoleView();
        consoleView.start();
    }
}