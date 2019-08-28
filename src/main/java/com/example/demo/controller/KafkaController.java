package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {
    private static final Logger logger = LoggerFactory.getLogger(KafkaController.class);

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private KafkaTemplate template;

    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public String send(@RequestParam(name = "message") String message) {
        String topic = "demo";
        template.send(topic, message);
        return "SEND";
    }
}
