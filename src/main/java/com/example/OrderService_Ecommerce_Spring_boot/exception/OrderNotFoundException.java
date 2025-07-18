package com.example.OrderService_Ecommerce_Spring_boot.exception;

public class OrderNotFoundException extends RuntimeException{

    public OrderNotFoundException(String message){
        super(message);
    }
}
