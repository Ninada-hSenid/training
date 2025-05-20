package com.trainingprogram.springsecurity_api.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class SignupRequest {
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    private String role; // Optional
}

