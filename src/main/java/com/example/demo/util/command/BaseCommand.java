package com.example.demo.util.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Component
public abstract class BaseCommand {

    @Autowired
    protected ConfigurableApplicationContext context;

    protected void config(String... args) {

    }

    protected void execute() {

    }

    public void run(String... args) {
        config(args);
        execute();
    }
}
