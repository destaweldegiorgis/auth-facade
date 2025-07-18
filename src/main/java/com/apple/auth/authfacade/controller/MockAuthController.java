package com.apple.auth.authfacade.controller;

import com.apple.auth.authfacade.dto.AuthResponse;
import com.apple.auth.authfacade.model.AuthRequest;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/mock")
@Profile("!prod")
public class MockAuthController {

    @PostMapping("/authorize")
    public Mono<ResponseEntity<AuthResponse>> mockAuthorize(@RequestBody AuthRequest request) {
        AuthResponse response = new AuthResponse(true);
        return Mono.just(ResponseEntity.ok(response));
    }
}

