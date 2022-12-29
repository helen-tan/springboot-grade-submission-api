package com.springbootgs.springbootgradesubmissionapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbootgs.springbootgradesubmissionapi.entity.Course;
import com.springbootgs.springbootgradesubmissionapi.entity.Grade;
import com.springbootgs.springbootgradesubmissionapi.entity.Student;
import com.springbootgs.springbootgradesubmissionapi.exception.GradeNotFoundException;
import com.springbootgs.springbootgradesubmissionapi.repository.CourseRepository;
import com.springbootgs.springbootgradesubmissionapi.repository.GradeRepository;
import com.springbootgs.springbootgradesubmissionapi.repository.StudentRepository;

@Service
public class GradeServiceImpl implements GradeService {

    @Autowired
    GradeRepository gradeRepository;
    
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    CourseRepository courseRepository;

    @Override
    public Grade getGrade(Long studentId, Long courseId) {
        Optional<Grade> grade = gradeRepository.findByStudentIdAndCourseId(studentId, courseId);

        if (grade.isPresent()) {
            return grade.get();
        } else {
            throw new GradeNotFoundException(studentId, courseId);
        }
    }

    @Override
    public Grade saveGrade(Grade grade, Long studentId, Long courseId) {
        // Assign grade to student
        // fetch student from repo first
        Student student = studentRepository.findById(studentId).get();
        Course course = courseRepository.findById(courseId).get();
        grade.setStudent(student); // assign the student to the grade
        grade.setCourse(course);   // assign a course

        return gradeRepository.save(grade);
    }

    @Override
    public Grade updateGrade(String score, Long studentId, Long courseId) {
        // Fetch a grade that already exists
        Optional<Grade> grade = gradeRepository.findByStudentIdAndCourseId(studentId, courseId);
        // If grade does not exist
        if (grade.isPresent()) {
            Grade unwrappedGrade = grade.get(); // Unwrap Optional
            // Update the grade object
            unwrappedGrade.setScore(score);
            // Save into DB
            return gradeRepository.save(unwrappedGrade);
        } else {
            throw new GradeNotFoundException(studentId, courseId);
        }
    }

    @Override
    public void deleteGrade(Long studentId, Long courseId) {
        
    }

    @Override
    public List<Grade> getStudentGrades(Long studentId) {
        return null;
    }

    @Override
    public List<Grade> getCourseGrades(Long courseId) {
        return null;
    }

    @Override
    public List<Grade> getAllGrades() {
        return null;
    }

}

