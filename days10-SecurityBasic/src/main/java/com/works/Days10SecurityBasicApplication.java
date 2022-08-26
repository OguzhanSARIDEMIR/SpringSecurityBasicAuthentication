package com.works;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Days10SecurityBasicApplication {

    public static void main(String[] args) {
        SpringApplication.run(Days10SecurityBasicApplication.class, args);
    }

}