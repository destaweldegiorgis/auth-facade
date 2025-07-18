package com.apple.auth.authfacade.controller;

import com.apple.auth.authfacade.dto.AuthResponse;
import com.apple.auth.authfacade.model.AuthRequest;
import com.apple.auth.authfacade.service.ReactiveAuthorizationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final ReactiveAuthorizationService reactiveAuthorizationService;

    @PostMapping("/authorize")
    public Mono<ResponseEntity<AuthResponse>> authorize(@Valid @RequestBody AuthRequest request) {
        log.info("🔐 Received API call for /authorize. userId={}, action={}, resource={}",
                request.getUserId(), request.getAction(), request.getResource());

        return reactiveAuthorizationService.authorize(request)
                .doOnNext(response -> log.info("Authorization evaluated for userId={}, result={}",
                        request.getUserId(), response.isAuthorized()))
                .map(ResponseEntity::ok)
                .doOnError(error -> log.error("Error during authorization for userId={}: {}", request.getUserId(), error.getMessage()));
    }
}
