package com.energycoffee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EnergyCoffeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnergyCoffeeApplication.class, args);
	}

}
