package com.springbootgs.springbootgradesubmissionapi.repository;

import org.springframework.data.repository.CrudRepository;

import com.springbootgs.springbootgradesubmissionapi.entity.Student;

// No need the @REpository annotation bcos Spring Boot is going to create an implementation of the interface which will exist as a bean
public interface StudentRepository extends CrudRepository<Student, Long> {
    
}
