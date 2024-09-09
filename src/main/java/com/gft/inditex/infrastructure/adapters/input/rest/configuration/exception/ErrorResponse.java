package com.gft.inditex.infrastructure.adapters.input.rest.configuration.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private String message;
    private int status;

    public ErrorResponse(String message, HttpStatus status) {
        this.message = message;
        this.status = status.value();
    }

}