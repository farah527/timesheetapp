package com.fastcode.timesheettestapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.fastcode.timesheettestapp"})
public class TimesheettestappApplication {

	public static void main(String[] args) {
		SpringApplication.run(TimesheettestappApplication.class, args);
	}

}

