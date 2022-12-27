package com.schenker.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import com.schenker.api.services.SpeciesService;

@Configuration
public class AppConfig {
    @Bean
    public SpeciesService categoryService() throws Exception {
        WebClient webClient = WebClient.builder()
                .baseUrl("http://localhost:8090/")
                .build();
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(webClient)).build();
        return factory.createClient(SpeciesService.class);
    }
}
