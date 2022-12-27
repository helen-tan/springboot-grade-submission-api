package com.springbootgs.springbootgradesubmissionapi.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity // Telling Spring Boot JPA to create a (student) table that stores student entities 
@Table(name = "student") // Name the table
@Getter // Lombok-enabled - generates getters based on defined fields
@Setter // Lombok-enabled - generates
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

}