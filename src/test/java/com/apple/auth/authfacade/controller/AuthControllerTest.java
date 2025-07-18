package com.apple.auth.authfacade.controller;

import com.apple.auth.authfacade.model.AuthRequest;
import com.apple.auth.authfacade.service.ReactiveAuthorizationService;
import com.apple.auth.authfacade.util.JwtUtil;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;

@WebFluxTest(controllers = AuthController.class)
@Import(AuthControllerTest.SecurityBypassConfig.class)
class AuthControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private ReactiveAuthorizationService reactiveAuthorizationService;

    @MockBean
    private JwtUtil jwtUtil;

    @Test
    void shouldReturnSuccessForValidRequest() {
        AuthRequest request = new AuthRequest("user1", "read", "file");

        Mockito.when(reactiveAuthorizationService.authorize(any()))
                .thenReturn(Mono.just(new com.apple.auth.authfacade.dto.AuthResponse(true)));

        webTestClient.post()
                .uri("/users/authorize")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .exchange()
                .expectStatus().isOk();
    }

    //  Override security for tests
    @TestConfiguration
    static class SecurityBypassConfig {
        @Bean
        public SecurityWebFilterChain testSecurityWebFilterChain(ServerHttpSecurity http) {
            return http.csrf(ServerHttpSecurity.CsrfSpec::disable)
                    .authorizeExchange(ex -> ex.anyExchange().permitAll())
                    .build();
        }
    }
}
