package com.apple.auth.authfacade.service;

import com.apple.auth.authfacade.model.AuthRequest;
import com.apple.auth.authfacade.dto.AuthResponse;

import java.util.concurrent.CompletableFuture;

public interface AuthorizationService {
    CompletableFuture<AuthResponse> authorize(AuthRequest request);
}