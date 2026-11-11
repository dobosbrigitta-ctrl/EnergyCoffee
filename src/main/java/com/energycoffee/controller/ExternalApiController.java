package com.energycoffee.controller;

import com.energycoffee.integration.ExternalApiClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/external")
public class ExternalApiController {

    private final ExternalApiClient externalApiClient;

    public ExternalApiController(ExternalApiClient externalApiClient) {
        this.externalApiClient = externalApiClient;
    }

    @GetMapping("/posts/1")
    public Mono<String> getExternalPost() {
        return externalApiClient.getExternalData();
    }
}