package com.example.demo.controller;

import com.example.demo.schedule.QuartzJob;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

@RestController
@RequestMapping(value = "/quartz")
public class QuartzController {
    private static final Logger logger = LoggerFactory.getLogger(QuartzController.class);

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private Scheduler scheduler;

    @RequestMapping(value = "/cron", method = RequestMethod.GET)
    public String cron(@RequestParam(name = "index") Integer index, @RequestParam(name = "time") Integer time)
                        throws SchedulerException {
        String name = "job" + index.toString();
        String group = "group" + index.toString();

        JobDetail jobDetail = JobBuilder.newJob()
                                        .ofType(QuartzJob.class)
                                        .withIdentity(name, group)
                                        .build();

        String cronExpression = String.format("0/%d * * * * ?", time);
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

        CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                                                .withIdentity(name, group)
                                                .withSchedule(scheduleBuilder)
                                                .build();

        scheduler.scheduleJob(jobDetail, cronTrigger);
        return "CRON";
    }

    @RequestMapping(value = "/once", method = RequestMethod.GET)
    public String once() throws SchedulerException {
        Integer index = 0;
        String name = "job" + index.toString();
        String group = "group" + index.toString();

        JobDetail jobDetail = JobBuilder.newJob()
                                        .ofType(QuartzJob.class)
                                        .withIdentity(name, group)
                                        .build();

        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                                                                    .withIntervalInSeconds(3)
                                                                    .withRepeatCount(0);

        SimpleTrigger simpleTrigger = TriggerBuilder.newTrigger()
                                                    .withIdentity(name, group)
                                                    .withSchedule(scheduleBuilder)
                                                    .build();

        scheduler.scheduleJob(jobDetail, simpleTrigger);
        return "ONCE";
    }

    @RequestMapping(value = "/kill", method = RequestMethod.GET)
    public JobKey stop(@RequestParam(name = "index") Integer index) throws SchedulerException {
        String name = "job" + index.toString();
        String group = "group" + index.toString();
        JobKey jobKey = new JobKey(name, group);
        scheduler.deleteJob(jobKey);
        return jobKey;
    }

    @RequestMapping(value = "/jobs", method = RequestMethod.GET)
    public Set jobs() throws SchedulerException {
        Set<TriggerKey> triggerKeys = scheduler.getTriggerKeys(GroupMatcher.anyTriggerGroup());
        Set<JobKey> jobKeys = scheduler.getJobKeys(GroupMatcher.anyJobGroup());     //获取所有的job集合
        return jobKeys;
    }
}
