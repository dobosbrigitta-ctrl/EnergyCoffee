
package com.energycoffee.controller;

import com.energycoffee.integration.ExternalApiClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/external")
public class ExternalApiController {

    private final ExternalApiClient externalApiClient;

    public ExternalApiController(ExternalApiClient externalApiClient) {
        this.externalApiClient = externalApiClient;
    }

    @GetMapping("/posts/{id}")
    public Mono<String> getExternalPost(@PathVariable Long id) {
        return externalApiClient.getExternalData(id);
    }
}
