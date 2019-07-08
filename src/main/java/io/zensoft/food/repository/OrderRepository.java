package io.zensoft.food.repository;

import io.zensoft.food.enums.OrderStatus;
import io.zensoft.food.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByUserIdAndStatus(Long userId, OrderStatus status);

    List<Order> findAllByUserId(Long userId);
}