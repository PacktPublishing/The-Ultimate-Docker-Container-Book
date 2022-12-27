package com.schenker.inventory;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/todos")
public class TodosController {
    private final RestTemplate restTemplate;

    public TodosController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public Todo[] getTodos() {
        var response = restTemplate.getForEntity("https://jsonplaceholder.typicode.com/todos", Todo[].class);
        return response.getBody();
    }
}
