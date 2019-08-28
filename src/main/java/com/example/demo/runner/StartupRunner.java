package com.example.demo.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupRunner implements CommandLineRunner {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void run(String... args) {
        logger.info("startup runner triggered");
    }
}
