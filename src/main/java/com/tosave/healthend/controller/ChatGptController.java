package com.tosave.healthend.controller;

import com.tosave.healthend.service.ChatGptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/chatgpt")
public class ChatGptController {

    private final ChatGptService chatGptService;

    @Autowired
    public ChatGptController(ChatGptService chatGptService) {
        this.chatGptService = chatGptService;
    }

    @PostMapping("/ask")
    public Mono<String> askChatGpt(@RequestBody String prompt) {
        return chatGptService.askGpt(prompt);
    }
}
