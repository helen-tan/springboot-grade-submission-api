package com.springbootgs.springbootgradesubmissionapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.springbootgs.springbootgradesubmissionapi.entity.Grade;

import jakarta.transaction.Transactional;

public interface GradeRepository extends CrudRepository<Grade, Long>{
    // Returns a Grade wrapped around an Optional
    // This warns whoever that is calling the method that the object sent back is at risk of being null
    // By wrapping the object in an Optional, the caller will not get a Null pointer exception, and can anticipate this scenario and handle the Optional
    Optional<Grade> findByStudentIdAndCourseId(Long studentId, Long courseId);

    @Transactional // Otherwise delete will have error (Common among deleteBy specialized methods)
    void deleteByStudentIdAndCourseId(Long studentId, Long courseId);

    List<Grade> findByStudentId(Long studentId);

    List<Grade> findByCourseId(Long courseId);
}
