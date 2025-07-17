package com.example.OrderService_Ecommerce_Spring_boot.mapper;

import com.example.OrderService_Ecommerce_Spring_boot.dto.OrderItemDTO;
import com.example.OrderService_Ecommerce_Spring_boot.entity.Order;
import com.example.OrderService_Ecommerce_Spring_boot.entity.OrderItem;

public class OrderItemMapper {

    public static OrderItem OrderItemRequestDTOtoOrderItemEntity(OrderItemDTO orderItemDTO,Order order,double pricePerUnit,double totalPrice){
        return OrderItem.builder()
                .productId(orderItemDTO.getProductId())
                .quantity(orderItemDTO.getQuantity())
                .pricePerUnit(pricePerUnit)
                .totalPrice(totalPrice)
                .order(order)
                .build();
    }
}
