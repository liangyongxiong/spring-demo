package com.example.demo.controller;

import com.example.demo.service.AsyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/async")
public class AsyncController {
    private static final Logger logger = LoggerFactory.getLogger(AsyncController.class);

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private AsyncService service;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String async() {
        logger.info("$$$$$$$$$$ async start $$$$$$$$$$");
        service.invoke();
        logger.info("$$$$$$$$$$ async end $$$$$$$$$$");
        return "ASYNC";
    }
}
