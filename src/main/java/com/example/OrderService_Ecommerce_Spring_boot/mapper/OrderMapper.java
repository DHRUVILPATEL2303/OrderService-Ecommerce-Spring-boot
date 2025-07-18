package com.example.OrderService_Ecommerce_Spring_boot.mapper;

import com.example.OrderService_Ecommerce_Spring_boot.dto.CreateOrderResponseDTO;
import com.example.OrderService_Ecommerce_Spring_boot.dto.OrderRequestDTO;
import com.example.OrderService_Ecommerce_Spring_boot.dto.UpdateOrderResponseDTO;
import com.example.OrderService_Ecommerce_Spring_boot.entity.Order;
import com.example.OrderService_Ecommerce_Spring_boot.enums.OrderStatus;

public class OrderMapper {

    public static Order toEntity(OrderRequestDTO orderRequestDTO){
        return Order.builder()
                .userId(orderRequestDTO.getUserId())
                .status(OrderStatus.PENDING)

                .build();
    }

    public static CreateOrderResponseDTO toCreateOrderResponseDTO(Order order){
        return CreateOrderResponseDTO.builder()
                .orderId(order.getId())
                .status(order.getStatus() )
                .build();
    }

    public static UpdateOrderResponseDTO toUpdateOrderToSucccessResponseDTO(Order order){
        return UpdateOrderResponseDTO.builder()
                .orderId(order.getId())
                .status(OrderStatus.COMPLETED)
                .build();
    }


    public static UpdateOrderResponseDTO toUpdateOrderToCancelResponseDTO(Order order){
        return UpdateOrderResponseDTO.builder()
                .orderId(order.getId())
                .status(OrderStatus.CANCELLED)
                .build();
    }
}
