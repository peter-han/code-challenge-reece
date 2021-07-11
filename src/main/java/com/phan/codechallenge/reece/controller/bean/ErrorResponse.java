package com.phan.codechallenge.reece.controller.bean;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ErrorResponse {
    Integer errorId;
    String message;

    public ErrorResponse(HttpStatus httpStatus) {
        errorId = httpStatus.value();
        message = httpStatus.getReasonPhrase();
    }
}
