package com.example.OrderService_Ecommerce_Spring_boot.controller;

import com.example.OrderService_Ecommerce_Spring_boot.dto.CreateOrderResponseDTO;
import com.example.OrderService_Ecommerce_Spring_boot.dto.OrderRequestDTO;
import com.example.OrderService_Ecommerce_Spring_boot.services.IOrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {


    private final IOrderService orderService;


    public OrderController(IOrderService iOrderService){
        this.orderService =iOrderService;
    }


    @PostMapping
    public ResponseEntity<CreateOrderResponseDTO> createOrder(@RequestBody OrderRequestDTO orderRequestDTO){
        CreateOrderResponseDTO createOrderResponseDTO = orderService.createOrder(orderRequestDTO);
        return ResponseEntity.ok(createOrderResponseDTO);
    }
}
