package com.university.lms.model;

import java.util.Objects;

public class Course {
    private int id;
    private String title;
    private String description;
    private int credits;
    private String instructor;

    public Course(int id, String title, String description, int credits, String instructor) {
        setId(id);
        setTitle(title);
        setDescription(description);
        setCredits(credits);
        setInstructor(instructor);
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        if (id <= 0) throw new IllegalArgumentException("Course ID must be positive");
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.isBlank())
            throw new IllegalArgumentException("Title cannot be empty");
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description != null ? description : "";
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        if (credits <= 0) throw new IllegalArgumentException("Credits must be positive");
        this.credits = credits;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        if (instructor == null || instructor.isBlank())
            throw new IllegalArgumentException("Instructor cannot be empty");
        this.instructor = instructor;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", credits=" + credits +
                ", instructor='" + instructor + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course)) return false;
        Course course = (Course) o;
        return id == course.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
