package com.springbootgs.springbootgradesubmissionapi;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.springbootgs.springbootgradesubmissionapi.entity.Course;
import com.springbootgs.springbootgradesubmissionapi.entity.Student;
import com.springbootgs.springbootgradesubmissionapi.repository.CourseRepository;
import com.springbootgs.springbootgradesubmissionapi.repository.StudentRepository;

@SpringBootApplication
public class SpringbootGradeSubmissionApiApplication implements CommandLineRunner {

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	CourseRepository courseRepository;

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
			new Student("Harry Potter", LocalDate.parse(("1980-07-31"))),
			new Student("Ron Weasley", LocalDate.parse(("1980-03-01"))),
            new Student("Hermione Granger", LocalDate.parse(("1979-09-19"))),
            new Student("Neville Longbottom", LocalDate.parse(("1980-07-30")))
		};
		
		// Iterate through student arr, and save into the database using the student repository method
		for(int i = 0; i < students.length; i++) {
			studentRepository.save(students[i]);
		}

		// Array of course entities
		Course[] courses = new Course[] {
            new Course("Charms", "CH104", "In this class, you will learn spells concerned with giving an object new and unexpected properties."),
            new Course("Defence Against the Dark Arts", "DADA", "In this class, you will learn defensive techniques against the dark arts."),
            new Course("Herbology", "HB311", "In this class, you will learn the study of magical plants and how to take care of, utilise and combat them."),
            new Course("History of Magic", "HIS393", "In this class, you will learn about significant events in wizarding history."),
            new Course("Potions", "POT102", "In this class, you will learn correct mixing and stirring of ingredients to create mixtures with magical effects."),
            new Course("Transfiguration", "TR442", "In this class, you will learn the art of changing the form or appearance of an object.")
        };

		// Iterate through courses arr, and save into the database using the course repository method
		for(int i = 0; i < students.length; i++) {
			courseRepository.save(courses[i]);
		}
	}
}
