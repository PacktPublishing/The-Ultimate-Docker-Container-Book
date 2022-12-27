package com.sample.javasample;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpeciesController {
	@GetMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}
}