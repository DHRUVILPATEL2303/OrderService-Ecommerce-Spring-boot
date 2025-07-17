package com.example.OrderService_Ecommerce_Spring_boot.repositry;

import com.example.OrderService_Ecommerce_Spring_boot.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepositry extends JpaRepository<Order,Long> {
}
