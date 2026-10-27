package com.energypulse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EnergyPulseApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnergyPulseApplication.class, args);
	}

}
