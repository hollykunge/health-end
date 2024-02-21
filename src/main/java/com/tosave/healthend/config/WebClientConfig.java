package com.tosave.healthend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient openAiWebClient() {
        return WebClient.builder()
                .baseUrl("https://api.openai.com")
                .defaultHeader("Authorization", "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") // 替换为你的OpenAI API Key
                .build();
    }
}
