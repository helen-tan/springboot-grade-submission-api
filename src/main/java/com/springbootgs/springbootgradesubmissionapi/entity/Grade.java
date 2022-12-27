package com.springbootgs.springbootgradesubmissionapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "grade")
@Getter // Lombok-enabled - generates getters based on defined fields
@Setter // Lombok-enabled - generates
@AllArgsConstructor
@NoArgsConstructor
public class Grade {

    @Id // this column is primary key - values uniquely identify a grade
    @GeneratedValue(strategy = GenerationType.IDENTITY) // every id is auto-generated
    @Column(name = "id") // column name
    private Long id;
    @Column(name = "score", nullable = false)
    private String score;

    // Tell Spring Boot that there will be a many-to-one relationship, as Spring JPA creates the grade table
    // Many grades will be associated with one student. Child table will manage the foreign key
    @ManyToOne 
    @JoinColumn(name = "student_id", referencedColumnName = "id") // Define foreign key column (needs to reference the primary key of another table)
    private Student student;
}