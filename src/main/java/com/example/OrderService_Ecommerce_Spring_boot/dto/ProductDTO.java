package com.example.OrderService_Ecommerce_Spring_boot.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {
    private Long id;
    private String title;
    private String brand;
    private String model;
    private String color;
    private int price;
    private int discount;
    private String image;
    private String description;
    private boolean popular;

    private Long categoryId;
}
