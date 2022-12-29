package com.springbootgs.springbootgradesubmissionapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbootgs.springbootgradesubmissionapi.entity.Course;
import com.springbootgs.springbootgradesubmissionapi.exception.CourseNotFoundException;
import com.springbootgs.springbootgradesubmissionapi.repository.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseRepository courseRepository;

    @Override
    public Course getCourse(Long id) {
        Optional<Course> course = courseRepository.findById(id); 
        return unwrapCourse(course, id);
        
        // if (course.isPresent()) {
        //     return course.get();
        // } else {
        //     throw new CourseNotFoundException(id);
        // }
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
        return (List<Course>) courseRepository.findAll();
    }

    // Function to unwrap the Course Optional entity
    static Course unwrapCourse(Optional<Course> entity, Long id) {
        if (entity.isPresent()) {
            return entity.get(); // .get() to unwrap the Optional
        } 
        else throw new CourseNotFoundException(id); // custom unchecked exception
    }

}