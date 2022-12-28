package com.springbootgs.springbootgradesubmissionapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "course")
@Getter // Lombok-enabled - generates getters based on defined fields
@Setter // Lombok-enabled - generates setters
@RequiredArgsConstructor
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NonNull
    @Column(name = "subject", nullable = false)
    private String subject;

    @NonNull
    @Column(name = "code", nullable = false)
    private String code;

    @NonNull
    @Column(name = "description", nullable = false)
    private String description;

}

