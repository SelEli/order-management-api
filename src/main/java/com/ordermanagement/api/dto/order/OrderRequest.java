package com.ordermanagement.api.dto.order;

import java.util.List;

public record OrderRequest(Long userId, List<OrderItemRequest> items) {}

public record OrderItemRequest(Long productId, Integer quantity) {}
