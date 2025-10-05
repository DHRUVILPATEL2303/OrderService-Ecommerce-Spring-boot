package com.example.OrderService_Ecommerce_Spring_boot.services;

import com.example.OrderService_Ecommerce_Spring_boot.clients.ProductServiceClient;
import com.example.OrderService_Ecommerce_Spring_boot.dto.*;
import com.example.OrderService_Ecommerce_Spring_boot.entity.Order;
import com.example.OrderService_Ecommerce_Spring_boot.entity.OrderItem;
import com.example.OrderService_Ecommerce_Spring_boot.enums.OrderStatus;
import com.example.OrderService_Ecommerce_Spring_boot.exception.OrderNotFoundException;
import com.example.OrderService_Ecommerce_Spring_boot.kafka.OrderKafkaProducer;
import com.example.OrderService_Ecommerce_Spring_boot.mapper.OrderItemMapper;
import com.example.OrderService_Ecommerce_Spring_boot.mapper.OrderMapper;
import com.example.OrderService_Ecommerce_Spring_boot.repositry.OrderRepositry;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService implements IOrderService {

    private final OrderRepositry orderRepositry;
    private final ProductServiceClient productServiceClient;
    private final OrderKafkaProducer orderKafkaProducer; // inject producer

    public OrderService(OrderRepositry orderRepositry, ProductServiceClient productServiceClient, OrderKafkaProducer orderKafkaProducer) {
        this.orderRepositry = orderRepositry;
        this.productServiceClient = productServiceClient;
        this.orderKafkaProducer = orderKafkaProducer;
    }

    @Override
    public CreateOrderResponseDTO createOrder(OrderRequestDTO orderRequestDTO) {
        Order order = OrderMapper.toEntity(orderRequestDTO);

        List<OrderItem> items = new ArrayList<>();
        double totalAmount = 0;

        for (OrderItemDTO itemDTO : orderRequestDTO.getOrderItems()) {
            ProductDTO productDTO = productServiceClient.getProductById(itemDTO.getProductId());
            double productPrice = productDTO.getPrice();
            double itemTotal = productPrice * itemDTO.getQuantity();
            totalAmount += itemTotal;

            OrderItem item = OrderItemMapper.OrderItemRequestDTOtoOrderItemEntity(itemDTO, order, productPrice, itemTotal);
            items.add(item);
        }

        order.setItems(items);
        Order createdOrder = orderRepositry.save(order);

        OrderEventDTO orderEventDTO = new OrderEventDTO(createdOrder.getId(), createdOrder.getStatus(), orderRequestDTO.getOrderItems(), totalAmount);
        orderKafkaProducer.sendOrderEvent(orderEventDTO);

        return OrderMapper.toCreateOrderResponseDTO(createdOrder);
    }

    @Override
    public UpdateOrderResponseDTO updateOrderStatusToSuccess(Long id) {
        Order order = orderRepositry.findById(id).orElseThrow(() -> new OrderNotFoundException("Order Not Found!! Check Order ID"));
        order.setStatus(OrderStatus.COMPLETED);
        orderRepositry.save(order);

        OrderEventDTO eventDTO = new OrderEventDTO(order.getId(), order.getStatus(), null, 0);
        orderKafkaProducer.sendOrderEvent(eventDTO);

        return OrderMapper.toUpdateOrderToSucccessResponseDTO(order);
    }

    @Override
    public UpdateOrderResponseDTO updateOrderStatusToCancel(Long id) {
        Order order = orderRepositry.findById(id).orElseThrow(() -> new OrderNotFoundException("Order Not Found!! Check OrderId"));
        order.setStatus(OrderStatus.CANCELLED);
        orderRepositry.save(order);

        OrderEventDTO eventDTO = new OrderEventDTO(order.getId(), order.getStatus(), null, 0);
        orderKafkaProducer.sendOrderEvent(eventDTO);

        return OrderMapper.toUpdateOrderToCancelResponseDTO(order);
    }
}
