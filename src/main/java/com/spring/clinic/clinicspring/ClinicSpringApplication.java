package com.spring.clinic.clinicspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClinicSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClinicSpringApplication.class, args);

		System.err.println("Hello World");
	}

}
