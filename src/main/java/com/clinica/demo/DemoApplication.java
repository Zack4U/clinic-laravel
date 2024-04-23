package com.clinica.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {

		Dotenv dotenv = Dotenv.load();

		setPropertyIfNotNull("DATASOURCE_USERNAME", dotenv.get("DATASOURCE_USERNAME"));
		setPropertyIfNotNull("DATASOURCE_PASSWORD", dotenv.get("DATASOURCE_PASSWORD"));
		setPropertyIfNotNull("GOOGLE_CLIENT_ID", dotenv.get("GOOGLE_CLIENT_ID"));
		setPropertyIfNotNull("GOOGLE_CLIENT_SECRET", dotenv.get("GOOGLE_CLIENT_SECRET"));

		SpringApplication.run(DemoApplication.class, args);
	}

	private static void setPropertyIfNotNull(String key, String value) {
		if (value != null) {
			System.setProperty(key, value);
		} else {
			System.out.println("Warning: " + key + " is not defined in the .env file");
		}
	}
}
