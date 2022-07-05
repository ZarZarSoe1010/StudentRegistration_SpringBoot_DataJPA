package com.studentRegistration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"com.studentRegistration.model"})
@EnableJpaRepositories(basePackages = "com.studentRegistration.dao")
public class StudentRegistrationSpringBootDataJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentRegistrationSpringBootDataJpaApplication.class, args);
	}

}
