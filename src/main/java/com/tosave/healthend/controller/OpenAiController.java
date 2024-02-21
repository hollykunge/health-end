package com.tosave.healthend.controller;

import com.tosave.healthend.service.OpenAiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
public class OpenAiController {

    private final OpenAiService openAiService;

    @Autowired
    public OpenAiController(OpenAiService openAiService) {
        this.openAiService = openAiService;
    }

    @PostMapping("/analyze-image")
    public Mono<String> analyzeImage(@RequestBody Map<String, String> request) {
        String imageUrl = request.get("imageUrl");
        return openAiService.analyzeImage(imageUrl);
    }
}
