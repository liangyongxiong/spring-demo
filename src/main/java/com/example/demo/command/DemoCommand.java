package com.example.demo.command;


import com.example.demo.util.command.BaseCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DemoCommand extends BaseCommand {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    protected void config(String... args) {
        super.config(args);
    }

    @Override
    protected void execute() {
        logger.info("demo command invokee");
    }
}
