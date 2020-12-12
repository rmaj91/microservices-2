package com.rmaj91.licensingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class LicensingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LicensingServiceApplication.class, args);
	}

}
