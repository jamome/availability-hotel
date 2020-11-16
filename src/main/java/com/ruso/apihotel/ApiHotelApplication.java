package com.ruso.apihotel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class ApiHotelApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiHotelApplication.class, args);
	}

}
