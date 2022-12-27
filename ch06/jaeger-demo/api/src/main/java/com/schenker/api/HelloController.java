package com.schenker.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HelloController {
    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);
    private RestTemplate restTemplate;

    public HelloController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/hello")
    public String hello() {
        logger.info("Requesting hello world!");
        return "Hello World!";
    }


    @GetMapping("/todos")
    public Todo[] getTodos() {
        logger.info("Requesting list of todos!");
        var todos = restTemplate.getForEntity("http://localhost:8090/api/todos", Todo[].class);
        return todos.getBody();
    }
}
