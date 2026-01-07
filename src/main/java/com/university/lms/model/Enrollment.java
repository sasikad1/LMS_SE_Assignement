package com.university.lms.model;


import java.util.Objects;

public class Enrollment {
    private int studentId;
    private int courseId;

    public Enrollment(int studentId, int courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "Enrollment{" +
                "studentId=" + studentId +
                ", courseId=" + courseId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Enrollment)) return false;
        Enrollment that = (Enrollment) o;
        return studentId == that.studentId && courseId == that.courseId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, courseId);
    }
}
