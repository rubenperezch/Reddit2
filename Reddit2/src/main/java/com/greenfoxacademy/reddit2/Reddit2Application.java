package com.greenfoxacademy.reddit2;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Reddit2Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Reddit2Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
