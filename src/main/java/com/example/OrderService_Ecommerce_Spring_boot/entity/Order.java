package com.example.OrderService_Ecommerce_Spring_boot.entity;

import com.example.OrderService_Ecommerce_Spring_boot.dto.OrderItemDTO;
import com.example.OrderService_Ecommerce_Spring_boot.enums.OrderStatus;
import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "orders")

public class Order extends BaseEntity {
    private Long userId;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> items;



}
