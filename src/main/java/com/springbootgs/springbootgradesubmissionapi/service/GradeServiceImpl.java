package com.springbootgs.springbootgradesubmissionapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbootgs.springbootgradesubmissionapi.entity.Course;
import com.springbootgs.springbootgradesubmissionapi.entity.Grade;
import com.springbootgs.springbootgradesubmissionapi.entity.Student;
import com.springbootgs.springbootgradesubmissionapi.exception.GradeNotFoundException;
import com.springbootgs.springbootgradesubmissionapi.exception.StudentNotEnrolledException;
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

        return unwrapGrade(grade, studentId, courseId);

        // if (grade.isPresent()) {
        //     return grade.get();
        // } else {
        //     throw new GradeNotFoundException(studentId, courseId);
        // }
    }

    @Override
    public Grade saveGrade(Grade grade, Long studentId, Long courseId) {
        // Assign grade to student
        // fetch student from repo first
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        Optional<Course> courseOptional = courseRepository.findById(courseId);

        // Unwrap the Optional with the unwrap function in the Service, which can catch the null error from unwrapping a null Optional / if there are no such student or course 
        Student student = StudentServiceImpl.unwrapStudent(studentOptional, courseId);
        Course course = CourseServiceImpl.unwrapCourse(courseOptional, courseId);

        // Check if student is enrolled in the course first (CANNOT ASSIGN STUDENT A GRADE IN A COURSE THEY ARE NOT ENROLLED IN)
        if (!student.getCourses().contains(course)) throw new StudentNotEnrolledException(studentId, courseId);

        grade.setStudent(student); // assign the student to the grade
        grade.setCourse(course);   // assign a course

        return gradeRepository.save(grade);
    }

    @Override
    public Grade updateGrade(String score, Long studentId, Long courseId) {
        // Fetch a grade that already exists
        Optional<Grade> grade = gradeRepository.findByStudentIdAndCourseId(studentId, courseId);
        Grade unwrappedGrade = unwrapGrade(grade, studentId, courseId);
        // Update the grade object
        unwrappedGrade.setScore(score);
        // Save into DB
        return gradeRepository.save(unwrappedGrade);

        // // If grade exist
        // if (grade.isPresent()) {
        //     Grade unwrappedGrade = grade.get(); // Unwrap Optional
        //     // Update the grade object
        //     unwrappedGrade.setScore(score);
        //     // Save into DB
        //     return gradeRepository.save(unwrappedGrade);
        // } else {
        //     throw new GradeNotFoundException(studentId, courseId);
        // }
    }

    @Override
    public void deleteGrade(Long studentId, Long courseId) {
        gradeRepository.deleteByStudentIdAndCourseId(studentId, courseId);
    }

    @Override
    public List<Grade> getStudentGrades(Long studentId) {
        return gradeRepository.findByStudentId(studentId);
    }

    @Override
    public List<Grade> getCourseGrades(Long courseId) {
        return gradeRepository.findByCourseId(courseId);
    }

    @Override
    public List<Grade> getAllGrades() {
        return (List<Grade>) gradeRepository.findAll();
    }

    // Unwarp the Grade Optional
    static Grade unwrapGrade(Optional<Grade> entity, Long studentId, Long courseId) {
       
        if (entity.isPresent()) {
            return entity.get();
        }  // If grade does not exist
        else throw new GradeNotFoundException(studentId, courseId);
    }

}

