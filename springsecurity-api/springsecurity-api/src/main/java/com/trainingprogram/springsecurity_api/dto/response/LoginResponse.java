package com.trainingprogram.springsecurity_api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data

public class LoginResponse {
    private String token;
    private String message; // Add this field

    // Modify constructor
    public LoginResponse(String token, String message) {
        this.token = token;
        this.message = message;
    }

    // Keep existing getters
    public String getToken() { return token; }

    // Add new getter
    public String getMessage() { return message; }
}
