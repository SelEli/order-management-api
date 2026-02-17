package com.ordermanagement.api.controller;

import com.ordermanagement.api.dto.order.OrderRequest;
import com.ordermanagement.api.dto.order.OrderResponse;
import com.ordermanagement.api.mapper.OrderMapper;
import com.ordermanagement.api.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @PostMapping
    public OrderResponse create(@RequestBody OrderRequest request) {
        return orderMapper.toResponse(orderService.create(request));
    }
}
