package com.example.OrderService_Ecommerce_Spring_boot.dto;

import com.example.OrderService_Ecommerce_Spring_boot.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderEventDTO {
    private Long orderId;
    private OrderStatus status;
    private List<OrderItemDTO> orderItems;
    private double totalAmount;
}