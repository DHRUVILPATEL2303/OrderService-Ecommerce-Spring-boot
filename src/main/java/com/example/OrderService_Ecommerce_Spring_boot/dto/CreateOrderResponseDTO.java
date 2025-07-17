package com.example.OrderService_Ecommerce_Spring_boot.dto;

import com.example.OrderService_Ecommerce_Spring_boot.enums.OrderStatus;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CreateOrderResponseDTO {
    private Long orderId;
    private OrderStatus status;

}
