package com.example.sorcier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SorcierNormalApplication {

	public static void main(String[] args) {
		SpringApplication.run(SorcierNormalApplication.class, args);
	}
}
