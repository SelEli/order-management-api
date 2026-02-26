package com.ordermanagement.api.service;

import com.ordermanagement.api.dto.order.OrderItemRequest;
import com.ordermanagement.api.dto.order.OrderRequest;
import com.ordermanagement.api.entity.Order;
import com.ordermanagement.api.entity.OrderItem;
import com.ordermanagement.api.entity.Product;
import com.ordermanagement.api.repository.OrderRepository;
import com.ordermanagement.api.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public Order create(OrderRequest request) {

        Order order = Order.builder()
                .userId(request.userId())
                .build();

        List<OrderItem> items = request.items().stream()
                .map(req -> toOrderItem(req, order))
                .toList();

        order.setItems(items);

        // --- RÈGLE MÉTIER : total calculé côté serveur ---
        BigDecimal total = items.stream()
                .map(i -> i.getUnitPrice().multiply(BigDecimal.valueOf(i.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        order.setTotal(total);

        return orderRepository.save(order);
    }

    private OrderItem toOrderItem(OrderItemRequest req, Order order) {

        // --- RÈGLE MÉTIER : prix figé au moment de la commande ---
        Product product = productRepository.findById(req.productId())
                .orElseThrow(() -> new RuntimeException("Produit introuvable"));

        BigDecimal price = BigDecimal.valueOf(product.getPrice());

        return OrderItem.builder()
                .productId(req.productId())
                .quantity(req.quantity())
                .unitPrice(price)
                .order(order)
                .build();
    }
}
