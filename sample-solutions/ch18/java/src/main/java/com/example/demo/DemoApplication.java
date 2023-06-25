package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.opentracing.Span;
import io.opentracing.Tracer;

@SpringBootApplication
@RestController
public class DemoApplication {

	@Autowired
	private Tracer tracer;
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

    @GetMapping("/")
    public String hello() {
        Span span = tracer.buildSpan("hello").start();
        String message = "Hello, World!";
        span.finish();
        return message;
    }
}
