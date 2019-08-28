package com.example.demo.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/hello")
public class HelloController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static class CustomizedResponse {
        @JsonProperty("text")
        private String content;

        public CustomizedResponse(String content) {
            this.setContent(content);
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

    @Autowired
    private HttpServletRequest request;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String plain() {
        logger.info("$$$$$$$$$$ info $$$$$$$$$$");
        logger.error("$$$$$$$$$$ error $$$$$$$$$$");
        return "world\n";
    }

    @RequestMapping(value = "/json", method = RequestMethod.GET)
    public CustomizedResponse json() {
        String userAgent = request.getHeader("User-Agent");
        return new CustomizedResponse(userAgent);
    }
}
