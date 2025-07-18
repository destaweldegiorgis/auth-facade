package com.apple.auth.authfacade.repository;

import com.apple.auth.authfacade.model.AuthRequest;
import com.apple.auth.authfacade.dto.AuthResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class MockAuthClient {

    // Simulate a downstream service via in-memory logic
    private static final Map<String, Boolean> AUTH_MAP = Map.of(
            "123:read:document-456", true,
            "124:write:document-457", false
    );

    public AuthResponse checkAuthorization(AuthRequest request) {
        String key = String.join(":", request.getUserId(), request.getAction(), request.getResource());
        log.info("Checking auth for key: {}", key);
        boolean allowed = AUTH_MAP.getOrDefault(key, false);
        return AuthResponse.builder().authorized(allowed).build();
    }
}