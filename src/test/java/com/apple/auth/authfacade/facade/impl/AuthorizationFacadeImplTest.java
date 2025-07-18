package com.apple.auth.authfacade.facade.impl;

import com.apple.auth.authfacade.dto.AuthResponse;
import com.apple.auth.authfacade.model.AuthRequest;
import com.apple.auth.authfacade.service.AuthorizationService;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AuthorizationFacadeImplTest {

    @Test
    public void testFacadeDelegatesToService() {
        AuthRequest req = new AuthRequest("user1", "read", "file");

        AuthorizationService mockService = mock(AuthorizationService.class);
        when(mockService.authorize(req)).thenReturn(CompletableFuture.completedFuture(new AuthResponse(true)));

        AuthorizationFacadeImpl facade = new AuthorizationFacadeImpl(mockService);

        AuthResponse result = facade.authorize(req).join();
        assertTrue(result.isAuthorized());
    }
}