package com.apple.auth.authfacade.service.impl;

import com.apple.auth.authfacade.dto.AuthResponse;
import com.apple.auth.authfacade.model.AuthRequest;
import com.apple.auth.authfacade.repository.ReactiveAuthClient;
import com.apple.auth.authfacade.service.ReactiveAuthorizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ReactiveAuthorizationServiceImpl implements ReactiveAuthorizationService {

    private final ReactiveAuthClient reactiveAuthClient;

    @Override
    public Mono<AuthResponse> authorize(AuthRequest request) {
        return reactiveAuthClient.checkAuthorization(request);
    }
}