package com.example.hospitalmanagements;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HospitalmanagementsApplication {

	public static void main(String[] args) throws Exception {
		
      //  com.example.hospitalmanagements.license.LicenseValidator.check();

		SpringApplication.run(HospitalmanagementsApplication.class, args);
	}


}
