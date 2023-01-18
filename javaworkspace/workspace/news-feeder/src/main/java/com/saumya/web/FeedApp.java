package com.saumya.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackageClasses=FeedController.class)
@SpringBootApplication
public class FeedApp {

	public static void main(String[] args) {
		SpringApplication.run(FeedApp.class, args);
	}

}
