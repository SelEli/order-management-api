package com.ordermanagement.api.dto.order;

import java.util.List;

public record OrderResponse(Long id, Long userId, List<OrderItemResponse> items) {}

public record OrderItemResponse(Long productId, Integer quantity) {}
