package com.personal.finance.client;

import com.personal.finance.dto.request.AIRequest;
import com.personal.finance.dto.response.AIResponse;
import com.personal.finance.exception.AIServiceUnavailableException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Component
@RequiredArgsConstructor
public class AIClient {

    private final WebClient aiWebClient;

    public AIResponse classify(AIRequest request) {
        try {
            return aiWebClient.post()
                    .uri("/classify")
                    .bodyValue(request)
                    .retrieve()
                    .bodyToMono(AIResponse.class)
                    .block();
        } catch (WebClientResponseException e) {
            throw new AIServiceUnavailableException("AI service is unavailable: " + e.getResponseBodyAsString(), e);
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred: " + e.getMessage(), e);
        }
    }
}
