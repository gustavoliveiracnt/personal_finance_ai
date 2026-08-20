package com.personal.finance.client.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${ai.base-url}")
    private String aiService;

    @Bean
    public WebClient aiWebClient() {
        return WebClient.builder()
                .baseUrl(aiService)
                .build();
    }
}
