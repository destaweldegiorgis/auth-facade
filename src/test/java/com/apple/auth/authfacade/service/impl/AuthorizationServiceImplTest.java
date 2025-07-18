package com.apple.auth.authfacade.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import com.apple.auth.authfacade.dto.AuthResponse;
import com.apple.auth.authfacade.model.AuthRequest;
import com.apple.auth.authfacade.repository.MockAuthClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class AuthorizationServiceImplTest {

    private MockAuthClient mockAuthClient;
    private AuthorizationServiceImpl authorizationService;

    @BeforeEach
    public void setUp() {
        mockAuthClient = mock(MockAuthClient.class);
        authorizationService = new AuthorizationServiceImpl(mockAuthClient);
    }

    @Test
    public void testAuthorize() {
        AuthRequest req = new AuthRequest("user1", "read", "file");
        when(mockAuthClient.checkAuthorization(req)).thenReturn(new AuthResponse(true));

        AuthResponse response = authorizationService.authorize(req).join();

        assertTrue(response.isAuthorized());
    }

}