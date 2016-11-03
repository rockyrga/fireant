package com.rga.fireant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableJpaRepositories
@EnableAsync
public class Application {

    public static final int TEXT_MAX_LENGTH = 65536;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
