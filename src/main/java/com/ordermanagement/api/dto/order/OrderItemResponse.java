package com.ordermanagement.api.dto.order;

public record OrderItemResponse(
        Long productId,
        Integer quantity
) {}
