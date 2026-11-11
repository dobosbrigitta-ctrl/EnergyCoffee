package com.energycoffee.integration;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class ExternalApiClient {

    private final WebClient webClient;

    public ExternalApiClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<String> getExternalData(Long id) {
        /*return webClient.get()
                .uri("/posts/{id}", id)
                .retrieve()
                .bodyToMono(String.class);*/
        return Mono.just("Hello World");
    }

}