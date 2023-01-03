package com.springbootgs.springbootgradesubmissionapi.service;

import java.util.List;

import com.springbootgs.springbootgradesubmissionapi.entity.Course;

public interface CourseService {
    Course getCourse(Long id);
    Course saveCourse(Course course);
    void deleteCourse(Long id);
    List<Course> getCourses();
    Course addStudentToCourse(Long studentId, Long courseId);
}
