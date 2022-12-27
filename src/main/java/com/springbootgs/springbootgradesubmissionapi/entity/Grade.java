package com.springbootgs.springbootgradesubmissionapi.entity;

import lombok.*;

@Getter // Lombok-enabled - generates getters based on defined fields
@Setter // Lombok-enabled - generates
@AllArgsConstructor
@NoArgsConstructor
public class Grade {

    private Long id;
    private String score;

}