package com.example.OrderService_Ecommerce_Spring_boot.services;

import com.example.OrderService_Ecommerce_Spring_boot.dto.CreateOrderResponseDTO;
import com.example.OrderService_Ecommerce_Spring_boot.dto.OrderRequestDTO;
import com.example.OrderService_Ecommerce_Spring_boot.dto.UpdateOrderResponseDTO;

public interface IOrderService {
    CreateOrderResponseDTO createOrder(OrderRequestDTO orderRequestDTO);
    UpdateOrderResponseDTO updateOrderStatusToSuccess(Long id);
    UpdateOrderResponseDTO updateOrderStatusToCancel(Long id);
}
