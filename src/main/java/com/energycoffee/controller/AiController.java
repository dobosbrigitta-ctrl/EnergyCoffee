package com.energycoffee.controller;

import com.energycoffee.service.AiService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
public class AiController {

    private final AiService aiService;

    public AiController(AiService aiService) {
        this.aiService = aiService;
    }

    @PostMapping("/ask")
    public String askAi(@RequestBody String prompt) {
        return aiService.generateResponse(prompt);
    }
}