package com.org.mortgageapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MortgageappApplication {

	public static void main(String[] args) {
		SpringApplication.run(MortgageappApplication.class, args);
	}
	

}
