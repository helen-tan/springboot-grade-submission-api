package com.springbootgs.springbootgradesubmissionapi.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message = "Subject cannot be blank")
    private String subject;

    @NonNull
    @Column(name = "code", nullable = false, unique = true)
    @NotBlank(message = "Course code cannot be blank")
    private String code;

    @NonNull
    @Column(name = "description", nullable = false)
    @NotBlank(message = "Description cannot be blank")
    private String description;

    // Create One-to-many relationship with grade
    @JsonIgnore
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Grade> grades;
}

