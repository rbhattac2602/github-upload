package com.greeting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Greeting {

    public static void main(String[] args) {
	System.out.println("Starting Greeting Application");
        SpringApplication.run(Greeting.class, args);
    }
}
