package com.springbootgs.springbootgradesubmissionapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbootgs.springbootgradesubmissionapi.entity.Student;
import com.springbootgs.springbootgradesubmissionapi.exception.StudentNotFoundException;
import com.springbootgs.springbootgradesubmissionapi.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Override
    public Student getStudent(Long id) {
        // Set student as an Optional (as it risks being a null, if no such student exist)
        Optional<Student> student = studentRepository.findById(id);
        return unwrapStudent(student, id);
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long id) {  
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> getStudents() {
        return (List<Student>) studentRepository.findAll();
    }

    // Function to unwrap the Student Optional entity
    static Student unwrapStudent(Optional<Student> entity, Long id) {
        if (entity.isPresent()) {
            return entity.get(); // .get() to unwrap the Optional
        } 
        else throw new StudentNotFoundException(id); // custom unchecked exception
    }



}
