package com.springbootgs.springbootgradesubmissionapi.repository;

import org.springframework.data.repository.CrudRepository;

import com.springbootgs.springbootgradesubmissionapi.entity.Grade;

public interface GradeRepository extends CrudRepository<Grade, Long>{
    Grade findByStudentId(Long studentId);
}
