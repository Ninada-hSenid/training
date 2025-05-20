package com.trainingprogram.springsecurity_api.dto.response;

public class ErrorResponse {
    private String error;
    private String message;

    public ErrorResponse(String error, String message) {
        this.error = error;
        this.message = message;
    }

    // Getters
    public String getError() { return error; }
    public String getMessage() { return message; }
}
