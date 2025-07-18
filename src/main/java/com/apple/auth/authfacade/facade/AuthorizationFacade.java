package com.apple.auth.authfacade.facade;

import com.apple.auth.authfacade.model.AuthRequest;
import com.apple.auth.authfacade.dto.AuthResponse;

import java.util.concurrent.CompletableFuture;

public interface AuthorizationFacade {
    CompletableFuture<AuthResponse> authorize(AuthRequest request);
}