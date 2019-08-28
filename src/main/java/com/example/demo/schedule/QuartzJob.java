package com.example.demo.schedule;

import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class QuartzJob extends QuartzJobBean {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final String formatPattern = "HH:mm:ss";

    @Override
    public void executeInternal(JobExecutionContext context) {
        String fireInstanceId = context.getFireInstanceId();
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern(formatPattern));
        logger.info(String.format("quartz %s : %s", fireInstanceId, timestamp));
    }
}
