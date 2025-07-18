package com.example.OrderService_Ecommerce_Spring_boot.services;

import com.example.OrderService_Ecommerce_Spring_boot.clients.ProductServiceClient;
import com.example.OrderService_Ecommerce_Spring_boot.dto.*;
import com.example.OrderService_Ecommerce_Spring_boot.entity.Order;
import com.example.OrderService_Ecommerce_Spring_boot.entity.OrderItem;
import com.example.OrderService_Ecommerce_Spring_boot.enums.OrderStatus;
import com.example.OrderService_Ecommerce_Spring_boot.exception.OrderNotFoundException;
import com.example.OrderService_Ecommerce_Spring_boot.mapper.OrderItemMapper;
import com.example.OrderService_Ecommerce_Spring_boot.mapper.OrderMapper;
import com.example.OrderService_Ecommerce_Spring_boot.repositry.OrderRepositry;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService implements IOrderService{

    private final OrderRepositry orderRepositry;
    private final ProductServiceClient productServiceClient;

    public OrderService(OrderRepositry orderRepositry, ProductServiceClient productServiceClient){
        this.orderRepositry=orderRepositry;
        this.productServiceClient = productServiceClient;
    }

    @Override
    public CreateOrderResponseDTO createOrder(OrderRequestDTO orderRequestDTO) {
        Order order = OrderMapper.toEntity(orderRequestDTO);


        List<OrderItem> items = new ArrayList<>();

        for (OrderItemDTO itemDTO : orderRequestDTO.getOrderItems()){
           ProductDTO productDTO= productServiceClient.getProductById(itemDTO.getProductId());
            double productPrice =productDTO.getPrice();
            double totalPrice = productPrice * itemDTO.getQuantity();
            OrderItem item = OrderItemMapper.OrderItemRequestDTOtoOrderItemEntity( itemDTO,order,productPrice,totalPrice);


            items.add(item);
        }
        order.setItems(items);
        Order createdorder =orderRepositry.save(order);

        return OrderMapper.toCreateOrderResponseDTO(createdorder);

    }

    @Override
    public UpdateOrderResponseDTO updateOrderStatusToSuccess(Long id) {
        Order order = orderRepositry.findById(id).orElseThrow(() -> new OrderNotFoundException("Order Not Found!! Check Order ID"));
        order.setStatus(OrderStatus.COMPLETED);

        orderRepositry.save(order);
        return OrderMapper.toUpdateOrderToSucccessResponseDTO(order);

    }

    @Override
    public UpdateOrderResponseDTO updateOrderStatusToCancel(Long id) {
        Order order = orderRepositry.findById(id).orElseThrow(() -> new OrderNotFoundException("Order Not Found!! Check OrderId"));
        order.setStatus(OrderStatus.CANCELLED);
        orderRepositry.save(order);
        return OrderMapper.toUpdateOrderToCancelResponseDTO(order);
    }
}
