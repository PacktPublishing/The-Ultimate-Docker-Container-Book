package com.example.library.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultController {
  @GetMapping("/")
  public String index() {
    return "Library component";
  }
}
