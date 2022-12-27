package com.springbootgs.springbootgradesubmissionapi.entity;

import lombok.*;

@Getter // Lombok-enabled - generates getters based on defined fields
@Setter // Lombok-enabled - generates
@AllArgsConstructor
@NoArgsConstructor
public class Course {

    private Long id;
    private String subject;
    private String code;
    private String description;

}

