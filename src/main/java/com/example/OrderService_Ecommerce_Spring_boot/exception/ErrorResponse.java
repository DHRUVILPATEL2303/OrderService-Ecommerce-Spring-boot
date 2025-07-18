package com.example.OrderService_Ecommerce_Spring_boot.exception;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponse {
    public int status;
    public String message;
    public LocalDateTime localDateTime;

}
