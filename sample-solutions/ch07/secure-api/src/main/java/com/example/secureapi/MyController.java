package com.example.secureapi;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

  @GetMapping("/api/protected/resource")
  @PreAuthorize("hasAuthority('SCOPE_read:resource')")
  public String getResource() {
    return "Protected resource";
  }

  @PostMapping("/api/protected/resource")
  @PreAuthorize("hasAuthority('SCOPE_write:resource')")
  public String addResource() {
    // add resource to DB
    return "Added protected resource";
  }
  
  @GetMapping("/api/unprotected/resource")
  public String getUnprotectedResource() {
    return "Unprotected resource";
  }

  @PostMapping("/api/unprotected/resource")
  public String addUnprotectedResource(){
    return "Unprotected resource added";
  }
}
