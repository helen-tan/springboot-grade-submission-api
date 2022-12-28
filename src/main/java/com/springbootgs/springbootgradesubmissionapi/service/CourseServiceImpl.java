package com.springbootgs.springbootgradesubmissionapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbootgs.springbootgradesubmissionapi.entity.Course;
import com.springbootgs.springbootgradesubmissionapi.repository.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseRepository courseRepository;

    @Override
    public Course getCourse(Long id) {
        return null;
    }

    @Override
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public void deleteCourse(Long id) {  
        courseRepository.deleteById(id);     
    }

    @Override
    public List<Course> getCourses() {
        return null;
    }

}