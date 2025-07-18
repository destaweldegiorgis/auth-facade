package com.apple.auth.authfacade.service.impl;

import com.apple.auth.authfacade.repository.MockAuthClient;
import com.apple.auth.authfacade.model.AuthRequest;
import com.apple.auth.authfacade.dto.AuthResponse;
import com.apple.auth.authfacade.service.AuthorizationService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthorizationServiceImpl implements AuthorizationService {

    private final MockAuthClient mockAuthClient;

    @Override
    @CircuitBreaker(name = "authCB", fallbackMethod = "fallback")
    @TimeLimiter(name = "authCB")
    public CompletableFuture<AuthResponse> authorize(AuthRequest request) {
        log.info("AuthorizationService: Processing request for userId={}, action={}, resource={}",
                request.getUserId(), request.getAction(), request.getResource());
        return CompletableFuture.supplyAsync(() -> mockAuthClient.checkAuthorization(request));
    }

    public CompletableFuture<AuthResponse> fallback(AuthRequest request, Throwable ex) {
        log.warn("Fallback triggered for userId={}, due to: {}", request.getUserId(), ex.toString());
        return CompletableFuture.completedFuture(AuthResponse.builder().authorized(false).build());
    }
}
