package com.springbootgs.springbootgradesubmissionapi.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbootgs.springbootgradesubmissionapi.entity.Course;
import com.springbootgs.springbootgradesubmissionapi.entity.Student;
import com.springbootgs.springbootgradesubmissionapi.exception.CourseNotFoundException;
import com.springbootgs.springbootgradesubmissionapi.repository.CourseRepository;
import com.springbootgs.springbootgradesubmissionapi.repository.StudentRepository;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    StudentRepository studentRepository;

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

    @Override
    public Course addStudentToCourse(Long studentId, Long courseId) {
        Course course = getCourse(courseId); // getting the passed-in core
        Optional<Student> student = studentRepository.findById(studentId); // getting the past-in core
    
        Student unwrappedStudent = StudentServiceImpl.unwrapStudent(student, studentId);

        course.getStudents().add(unwrappedStudent);

        return courseRepository.save(course);
    }

    @Override
    public Set<Student> getEnrolledStudents(Long id) {
        Course course = getCourse(id);
        return course.getStudents();
    }

    // Function to unwrap the Course Optional entity
    static Course unwrapCourse(Optional<Course> entity, Long id) {
        if (entity.isPresent()) {
            return entity.get(); // .get() to unwrap the Optional
        } 
        else throw new CourseNotFoundException(id); // custom unchecked exception
    }

}