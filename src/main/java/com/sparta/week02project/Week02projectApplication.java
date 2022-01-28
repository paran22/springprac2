package com.sparta.week02project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@EnableJpaAuditing
@SpringBootApplication
public class Week02projectApplication {

    public static void main(String[] args) {
        SpringApplication.run(Week02projectApplication.class, args);
    }

}
