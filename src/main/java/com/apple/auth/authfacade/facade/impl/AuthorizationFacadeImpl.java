package com.apple.auth.authfacade.facade.impl;

import com.apple.auth.authfacade.model.AuthRequest;
import com.apple.auth.authfacade.dto.AuthResponse;
import com.apple.auth.authfacade.facade.AuthorizationFacade;
import com.apple.auth.authfacade.service.AuthorizationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthorizationFacadeImpl implements AuthorizationFacade {

    private final AuthorizationService authorizationService;

    @Override
    public CompletableFuture<AuthResponse> authorize(AuthRequest request) {
        log.debug("Facade received request for: {}", request.getUserId());
        return authorizationService.authorize(request);
    }
}