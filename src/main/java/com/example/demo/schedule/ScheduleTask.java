package com.example.demo.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class ScheduleTask {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final String formatPattern = "HH:mm:ss";

    @Scheduled(fixedRate = 1000 * 30)
    public void fixedRate(){
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern(formatPattern));
        logger.info("fixedRate ：" + timestamp);
    }

    @Scheduled(cron = "15 */2 * * * ?")
    public void cron(){
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern(formatPattern));
        logger.info("cron ：" + timestamp);
    }
}
