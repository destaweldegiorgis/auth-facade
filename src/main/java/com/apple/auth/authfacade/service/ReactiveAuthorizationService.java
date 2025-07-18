
package com.apple.auth.authfacade.service;

import com.apple.auth.authfacade.dto.AuthResponse;
import com.apple.auth.authfacade.model.AuthRequest;
import reactor.core.publisher.Mono;

public interface ReactiveAuthorizationService {
    Mono<AuthResponse> authorize(AuthRequest request);
}
