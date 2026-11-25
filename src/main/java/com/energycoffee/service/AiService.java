package com.energycoffee.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class AiService {

    private final ChatClient chatClient;

    public AiService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public String generateResponse(String prompt) {
        return chatClient.prompt()
                .system("""
                        You are an energy analysis assistant for the EnergyCoffee application.
                        Your role is to help users understand energy consumption, energy efficiency and energy costs.

                        Follow these rules:
                        - Provide clear, accurate and professional answers.
                        - Use understandable technical language.
                        - Never invent measurements, statistics or energy consumption data.
                        - Clearly distinguish facts from assumptions.
                        - If information is insufficient, explicitly state what is missing.
                        """)
                .user(prompt)
                .call()
                .content();
    }
}