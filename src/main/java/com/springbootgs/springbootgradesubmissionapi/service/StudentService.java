package com.springbootgs.springbootgradesubmissionapi.service;

import java.util.List;

import com.springbootgs.springbootgradesubmissionapi.entity.Student;

public interface StudentService {
    Student getStudent(Long id);
    Student saveStudent(Student student);
    void deleteStudent(Long id);
    List<Student> getStudents();
}