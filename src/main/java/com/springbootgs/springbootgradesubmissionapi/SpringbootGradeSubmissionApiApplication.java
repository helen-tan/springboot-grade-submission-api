package com.springbootgs.springbootgradesubmissionapi;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.springbootgs.springbootgradesubmissionapi.entity.Student;
import com.springbootgs.springbootgradesubmissionapi.repository.StudentRepository;

@SpringBootApplication
public class SpringbootGradeSubmissionApiApplication implements CommandLineRunner {

	@Autowired
	StudentRepository studentRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootGradeSubmissionApiApplication.class, args);
	}

	// Override the run method when implementing the interface CommandLinerunner
	// run method is invoked when we run the Spring Boot app
	@Override
	public void run(String... args) throws Exception {
		// Save 4 students in the student table
		// Array of student entitites
		Student[] students = new Student[] {
			new Student(1L, "Harry Potter", LocalDate.parse(("1980-07-31"))),
			new Student(2L, "Ron Weasley", LocalDate.parse(("1980-03-01"))),
            new Student(3L, "Hermione Granger", LocalDate.parse(("1979-09-19"))),
            new Student(4L, "Neville Longbottom", LocalDate.parse(("1980-07-30")))
		};
		
		// Iterate through student arr, and save intot he database using the student repository method
		for(int i = 0; i < students.length; i++) {
			studentRepository.save(students[i]);
		}
	}
}
