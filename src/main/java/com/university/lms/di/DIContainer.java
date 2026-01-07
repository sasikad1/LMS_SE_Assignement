//package com.university.lms.di;
//
//import com.university.lms.service.*;
//import com.university.lms.service.impl.*;
//
//public class DIContainer {
//    private static DIContainer instance;
//    private final StudentService studentService;
//    private final CourseService courseService;
//    private final EnrollmentService enrollmentService;
//
//    private DIContainer() {
//        // Create services
//        this.studentService = new StudentServiceImpl();
//        this.courseService = new CourseServiceImpl();
//        this.enrollmentService = new EnrollmentServiceImpl(studentService, courseService);
//    }
//
//    public static DIContainer getInstance() {
//        if (instance == null) {
//            instance = new DIContainer();
//        }
//        return instance;
//    }
//
//    public StudentService getStudentService() {
//        return studentService;
//    }
//
//    public CourseService getCourseService() {
//        return courseService;
//    }
//
//    public EnrollmentService getEnrollmentService() {
//        return enrollmentService;
//    }
//}