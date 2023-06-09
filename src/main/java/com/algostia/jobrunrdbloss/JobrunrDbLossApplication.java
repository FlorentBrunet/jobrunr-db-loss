package com.algostia.jobrunrdbloss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class JobrunrDbLossApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobrunrDbLossApplication.class, args);
    }

}
