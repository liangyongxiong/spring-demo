package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/redis")
public class RedisController {
    private static final Logger logger = LoggerFactory.getLogger(RedisController.class);

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private RedisTemplate template;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String get(@RequestParam(name = "key") String key) {
        ValueOperations operations = template.opsForValue();
        String value = (String)operations.get(key);
        if (value == null) {
            return "NULL";
        }
        return value;
    }

    @RequestMapping(value = "/set", method = RequestMethod.GET)
    public String set(@RequestParam(name ="key") String key,
                      @RequestParam(name ="value", required=false) String value) {
        if (value == null) {
            return "NULL";
        }
        ValueOperations operations = template.opsForValue();
        operations.set(key, value);
        return value;
    }

    @RequestMapping(value = "/db", method = RequestMethod.GET)
    public String switchDB(@RequestParam(name = "db") Integer db, @RequestParam(name = "key") String key) {
        JedisConnectionFactory factory = (JedisConnectionFactory) template.getConnectionFactory();
        factory.getStandaloneConfiguration().setDatabase(db);
        ValueOperations operations = template.opsForValue();
        String value = (String)operations.get(key);
        if (value == null) {
            return "NULL";
        }
        return value;
    }
}
