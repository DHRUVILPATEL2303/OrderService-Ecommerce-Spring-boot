package com.example.OrderService_Ecommerce_Spring_boot.dto;

import jakarta.persistence.Entity;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequestDTO {
    private Long userId;
    private List<OrderItemDTO> orderItems;
}
