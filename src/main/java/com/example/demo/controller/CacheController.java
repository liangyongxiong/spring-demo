package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/cache")
@CacheConfig(cacheNames = "cache")
public class CacheController {
    private static final Logger logger = LoggerFactory.getLogger(CacheController.class);

    @Autowired
    private HttpServletRequest request;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @Cacheable(keyGenerator = "cacheKeyGenerator")
    public String get(@RequestParam(name = "key") String key) {
        return "get";
    }

    @RequestMapping(value = "/put", method = RequestMethod.GET)
    @CachePut(keyGenerator = "cacheKeyGenerator")
    public String put(@RequestParam(name = "key") String key) {
        return "put";
    }

    @RequestMapping(value = "/evict", method = RequestMethod.GET)
    @CacheEvict(keyGenerator = "cacheKeyGenerator")
    public void evict(@RequestParam(name = "key") String key) {
        return;
    }
}
