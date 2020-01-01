package com.authentication.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.authentication")
@EntityScan("com.authentication.model")
@EnableJpaRepositories("com.authentication.repository")
public class MainApplication extends SpringBootServletInitializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
        LOGGER.info("Start application");
    }
}
