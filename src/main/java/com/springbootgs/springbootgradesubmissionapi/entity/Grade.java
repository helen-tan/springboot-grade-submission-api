package com.springbootgs.springbootgradesubmissionapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    @Column(name = "id")
    private Long id;
    @Column(name = "score")
    private String score;

}