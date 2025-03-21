// Package declaration: Defines the package in which this class resides
package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication is a combination of @Configuration, @EnableAutoConfiguration, and @ComponentScan
// It marks this class as the main entry point for the Spring Boot application.
@SpringBootApplication
public class MainApp {

    // The main() method serves as the application's entry point.
    public static void main(String[] args) {
        // SpringApplication.run() launches the Spring Boot application.
        SpringApplication.run(MainApp.class, args);
    }
}
