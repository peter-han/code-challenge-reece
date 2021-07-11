package com.phan.codechallenge.reece.controller.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {
    Integer errorId;
    String message;
}
