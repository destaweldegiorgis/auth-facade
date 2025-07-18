package com.apple.auth.authfacade.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.apple.auth.authfacade.dto.AuthResponse;
import com.apple.auth.authfacade.model.AuthRequest;
import org.junit.jupiter.api.Test;

public class MockAuthClientTest {

    private final MockAuthClient client = new MockAuthClient();

    @Test
    public void testAuthorizedUser() {
        AuthRequest req = new AuthRequest("123", "read", "document-456");
        AuthResponse res = client.checkAuthorization(req);
        assertTrue(res.isAuthorized());
    }

    @Test
    public void testUnauthorizedUser() {
        AuthRequest req = new AuthRequest("userX", "write", "file");
        AuthResponse res = client.checkAuthorization(req);
        assertFalse(res.isAuthorized());
    }
}