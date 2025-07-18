package com.apple.auth.authfacade.repository;

import com.apple.auth.authfacade.dto.AuthResponse;
import com.apple.auth.authfacade.model.AuthRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class ReactiveAuthClient {

    private final WebClient.Builder webClientBuilder;

    @Value("${downstream.mock-url}")
    private String mockUrl;

    public Mono<AuthResponse> checkAuthorization(AuthRequest request) {
        log.info("Calling downstream reactive mock service at {}", mockUrl);

        return webClientBuilder.build()
                .post()
                .uri(mockUrl)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(AuthResponse.class)
                .doOnNext(response ->
                        log.info("Received reactive response: authorized={}", response.isAuthorized()))
                .doOnError(error ->
                        log.error("Reactive downstream call failed: {}", error.getMessage()));
    }
}