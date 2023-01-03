package com.springbootgs.springbootgradesubmissionapi.entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity // Telling Spring Boot JPA to create a (student) table that stores student entities 
@Table(name = "student") // Name the table
@Getter // Lombok-enabled - generates getters based on defined fields
@Setter // Lombok-enabled - generates
@RequiredArgsConstructor // Lombok - Generates a constructor w required arguments (marked w @NonNull)
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NonNull
    @Column(name = "name", nullable = false)
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NonNull
    @Column(name = "birth_date", nullable = false)
    @Past(message = "The birth date must be in the past")
    private LocalDate birthDate;

    // Tell Spring Boot that there will be a one-to-many relationship, as Spring JPA creates the grade table
    // one-to-many relationship between 1 student and a List of grades
    // mappedBy: Need to tell JPA that the relationship is aldy mapped by the owner of the relationship/ the one managing the foreign key. Otherwise Spring will create a join table in an attempt to map the relationship
    // Set mappedBy to the property that maps the relationship
    @JsonIgnore // So that grades list will not be part of json response. When we return a student entity and serializing its fields into json, we cannot allow the List of entities to be part of the serialization process
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL) 
    private List<Grade> grades;

    // Constructor with all fields - generated with Lombok's @AllArgsContructor
    // public Student(Long id, String name, LocalDate birthDate) {
    //     this.id = id;
    //     this.name = name;
    //     this.birthDate = birthDate;
    // }
    
    // Empty constructor - generated by Lombok's @NoArgsConstructor
    // public Student() {
    // }
    
    // Create Many-to-Many relationship with course
    // mappedBy is put on the non-owning side of the relationship (which is Student bcos we decided that Course owns the relatinship)
    // without mappedBy, Sping JPA will automatically create another join table
    @ManyToMany(mappedBy = "students") 
    private List<Course> courses;
}