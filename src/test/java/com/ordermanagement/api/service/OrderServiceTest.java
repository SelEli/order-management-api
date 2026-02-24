package com.ordermanagement.api.service;

import com.ordermanagement.api.dto.order.OrderItemRequest;
import com.ordermanagement.api.dto.order.OrderRequest;
import com.ordermanagement.api.entity.Order;
import com.ordermanagement.api.entity.OrderItem;
import com.ordermanagement.api.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceTest {

    private OrderRepository orderRepository;
    private OrderService orderService;

    @BeforeEach
    void setup() {
        orderRepository = mock(OrderRepository.class);
        orderService = new OrderService(orderRepository);
    }

    @Test
    void create_shouldBuildOrderWithItems() {
        OrderItemRequest itemReq = new OrderItemRequest(1L, 2);
        OrderRequest req = new OrderRequest(10L, List.of(itemReq));

        when(orderRepository.save(any(Order.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Order order = orderService.create(req);

        assertEquals(10L, order.getUserId());
        assertEquals(1, order.getItems().size());

        OrderItem item = order.getItems().get(0);
        assertEquals(1L, item.getProductId());
        assertEquals(2, item.getQuantity());
        assertEquals(order, item.getOrder());

        verify(orderRepository).save(order);
    }
}
