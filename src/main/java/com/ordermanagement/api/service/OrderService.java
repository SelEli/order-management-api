package com.ordermanagement.api.service;

import com.ordermanagement.api.dto.order.OrderItemRequest;
import com.ordermanagement.api.dto.order.OrderRequest;
import com.ordermanagement.api.entity.Order;
import com.ordermanagement.api.entity.OrderItem;
import com.ordermanagement.api.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Order create(OrderRequest request) {
        Order order = Order.builder()
                .userId(request.userId())
                .build();

        List<OrderItem> items = request.items().stream()
                .map(req -> toOrderItem(req, order))
                .toList();

        order.setItems(items);
        return orderRepository.save(order);
    }

    private OrderItem toOrderItem(OrderItemRequest req, Order order) {
        return OrderItem.builder()
                .productId(req.productId())
                .quantity(req.quantity())
                .order(order)
                .build();
    }
}
