package com.tosave.healthend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
public class ChatGptService {

    private final WebClient webClient;

    public ChatGptService(@Value("${openai.api.key}") String apiKey) {
        this.webClient = WebClient.builder()
                .baseUrl("https://api.openai.com/v1")
                .defaultHeader("Authorization", "Bearer " + apiKey)
                .build();
    }

    public Mono<String> askGpt(String prompt) {
        return webClient.post()
                .uri("/chat/completions")
                .bodyValue(Map.of(
                    "model", "gpt-3.5-turbo",
                    "prompt", prompt,
                    "temperature", 0.5,
                    "max_tokens", 100
                ))
                .retrieve()
                .bodyToMono(String.class); // 或者使用更具体的响应类型来处理JSON响应
    }
}
