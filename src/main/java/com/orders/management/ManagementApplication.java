package com.orders.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * main class from where our project bootstraps or starts
 *
 * @author Amit Malik
 *
 */
@SpringBootApplication
@EnableScheduling
public class ManagementApplication {

	public static void main(final String[] args) {
		SpringApplication.run(ManagementApplication.class, args);
	}

}