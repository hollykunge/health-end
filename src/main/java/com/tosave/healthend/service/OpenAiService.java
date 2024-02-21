package com.tosave.healthend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Service
public class OpenAiService {

    private final WebClient webClient;

    @Autowired
    public OpenAiService(WebClient openAiWebClient) {
        this.webClient = openAiWebClient;
    }

    public Mono<String> analyzeImage(String imageUrl) {
        return webClient.post()
                .uri("/v1/chat/completions")
                .bodyValue(Map.of(
                        "model", "gpt-4-vision-preview",
                        "messages", List.of(
                                Map.of(
                                        "role", "user",
                                        "content", List.of(
                                                Map.of("type", "text", "text", "What’s in this image?"),
                                                Map.of("type", "image_url", "image_url", Map.of("url", imageUrl))
                                        )
                                )
                        )
                ))
                .retrieve()
                .bodyToMono(String.class); // 根据实际需要可能需要调整返回类型
    }
}
