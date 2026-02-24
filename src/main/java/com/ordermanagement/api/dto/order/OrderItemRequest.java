package com.ordermanagement.api.dto.order;

public record OrderItemRequest(
        Long productId,
        Integer quantity
) {}
