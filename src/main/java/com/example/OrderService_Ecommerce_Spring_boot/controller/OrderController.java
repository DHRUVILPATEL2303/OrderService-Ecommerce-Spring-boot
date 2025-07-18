package com.example.OrderService_Ecommerce_Spring_boot.controller;

import com.example.OrderService_Ecommerce_Spring_boot.dto.CreateOrderResponseDTO;
import com.example.OrderService_Ecommerce_Spring_boot.dto.OrderRequestDTO;
import com.example.OrderService_Ecommerce_Spring_boot.dto.UpdateOrderResponseDTO;
import com.example.OrderService_Ecommerce_Spring_boot.exception.OrderNotFoundException;
import com.example.OrderService_Ecommerce_Spring_boot.services.IOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PatchMapping("/update/{id}")
    public ResponseEntity<UpdateOrderResponseDTO> updateOrder(@PathVariable("id") Long orderId){
        UpdateOrderResponseDTO updateOrderResponseDTO=orderService.updateOrder(orderId);
        return ResponseEntity.ok(updateOrderResponseDTO);
    }


//    @ExceptionHandler(OrderNotFoundException.class)
//    public ResponseEntity<String> handleOrderNotFound(OrderNotFoundException orderNotFoundException){
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(orderNotFoundException.getMessage());
//
//    }
}
