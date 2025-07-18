package com.apple.auth.authfacade.model;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthRequest {
    @NotBlank(message = "userId must not be blank")
    private String userId;
    @NotBlank(message = "action must not be blank")
    private String action;
    @NotBlank(message = "resource must not be blank")
    private String resource;
}
