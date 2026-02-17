package com.ordermanagement.api.mapper;

import com.ordermanagement.api.dto.order.OrderItemResponse;
import com.ordermanagement.api.dto.order.OrderResponse;
import com.ordermanagement.api.entity.Order;
import com.ordermanagement.api.entity.OrderItem;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderMapper {

    public OrderResponse toResponse(Order order) {
        List<OrderItemResponse> items = order.getItems().stream()
                .map(this::toItemResponse)
                .toList();

        return new OrderResponse(order.getId(), order.getUserId(), items);
    }

    private OrderItemResponse toItemResponse(OrderItem item) {
        return new OrderItemResponse(item.getProductId(), item.getQuantity());
    }
}
