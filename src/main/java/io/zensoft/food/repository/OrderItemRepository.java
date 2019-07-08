package io.zensoft.food.repository;

import io.zensoft.food.model.Order;
import io.zensoft.food.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    Optional<OrderItem> findByOrderAndDishId(Order order, Long dishId);
}
