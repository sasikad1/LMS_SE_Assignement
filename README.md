# University Learning Management System (LMS)

A robust, refactored Java-based Console Application designed for managing students, courses, and enrollments. This project demonstrates advanced Software Engineering principles, including **Three-Tier MVC Architecture**, **SOLID Principles**, and **Automated Testing**.

---

## 🏗System Architecture
The system has been refactored from a monolithic structure to a **Three-Tier Architecture** to ensure loose coupling and high maintainability.



1.  **Presentation Layer (View):** Handles user interactions and console I/O.
2.  **Logic Layer (Controller):** Mediates between the View and Service layers.
3.  **Service Layer (Business Logic):** Handles core computations, filtering, and data integrity using Java Streams.

---

## Key Features
- **Student Management:** Full CRUD operations with functional searching and sorting.
- **Course Management:** Efficient course handling using `HashMap` for $O(1)$ retrieval.
- **Enrollment System:** Ensures referential integrity between students and courses.
- **Reflection Engine:** Demonstrates metaprogramming by inspecting class metadata at runtime.
- **Functional Programming:** Extensively uses Java Streams and Lambda expressions for data processing.

---

## Testing Suite
The project includes a comprehensive **JUnit 5** test suite covering all layers to ensure zero-regressions.

- **Service Tests:** Verifies business logic (e.g., `StudentServiceImplTest`).
- **Controller Tests:** Verifies delegation logic.
- **Integration Tests:** Verifies UI flow and service interaction (e.g., `EnrollmentViewTest`).



---

## Project Structure
```text
src/
├── main/java/com/university/lms/
│   ├── model/         # Domain Entities (Student, Course, Enrollment)
│   ├── view/          # Console UI Components
│   ├── controller/    # Request Handlers
│   ├── service/       # Business Logic Interfaces & Implementations
│   └── util/          # Reflection Engine & Helper Utilities
└── test/java/com/university/lms/
    └── (Tests for all the above layers)


```
---
## 🛠How to Run

To get the system up and running on your local machine, follow these steps:

1.  **Prerequisites:** Ensure you have **JDK 17** or higher installed on your system.
2.  **IDE Setup:** Open the project folder using an IDE like **IntelliJ IDEA** (recommended), Eclipse, or VS Code.
3.  **Dependency Check:** If using Maven, ensure dependencies are loaded. If not, verify that JUnit 5 libraries are added to the project's library path.
4.  **Execution:** Locate the `com.university.lms.App` class and run the `main` method.
5.  **Testing:** * Navigate to the `src/test/java` directory.
    * Right-click on the folder and select **Run 'All Tests'**.
    * Ensure all test cases display a **green checkmark**.



---

## Refactoring Applied

The system underwent significant refactoring to transform it from a "Big Ball of Mud" into a clean, maintainable architecture. The following **Code Smells** were successfully addressed:

### **1. God Class & Long Methods**
* **Issue:** The initial `App.java` contained all logic, making it over 500 lines long.
* **Solution:** Logic was extracted into specialized **Service** and **Controller** classes. Methods were broken down using the *Extract Method* technique to ensure each method performs only one task.



### **2. Inappropriate Intimacy**
* **Issue:** Classes were accessing each other's private data or internal lists directly.
* **Solution:** Implemented **Dependency Injection (DI)**. Services now communicate via interfaces, and the `EnrollmentService` depends on `StudentService` and `CourseService` abstractions rather than internal implementations.

### **3. Magic Numbers**
* **Issue:** Hardcoded integers (e.g., `1`, `2`, `0`) were used for menu choices.
* **Solution:** Replaced with meaningful **Static Constants** (e.g., `ADD_STUDENT = 1`, `EXIT = 0`) to improve code readability.

### **4. Feature Envy**
* **Issue:** Certain classes were more interested in the data of other classes than their own.
* **Solution:** Moved business logic into the appropriate **Service layer** where the data resides, adhering to the *Expert* principle of GRASP.



---