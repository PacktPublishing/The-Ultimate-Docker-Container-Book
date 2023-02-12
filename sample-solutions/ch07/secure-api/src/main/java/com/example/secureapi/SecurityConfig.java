package com.example.secureapi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
      .mvcMatchers(HttpMethod.GET, "/api/unprotected/**").permitAll()
      .mvcMatchers(HttpMethod.POST, "/api/unprotected/**").permitAll()
      .anyRequest()
      .authenticated()
      .and()
      .addFilter(new JwtAuthorizationFilter(authenticationManager()));
      // .oauth2ResourceServer()
      // .jwt();

    //http.addFilter(new JwtAuthorizationFilter(authenticationManager()));
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManager() throws Exception {
    return super.authenticationManager();
  }
}
