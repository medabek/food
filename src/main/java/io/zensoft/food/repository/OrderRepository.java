package io.zensoft.food.repository;

import io.zensoft.food.enums.OrderStatus;
import io.zensoft.food.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long>,
        PagingAndSortingRepository<Order, Long> {
    Optional<Order> findByUserIdAndStatus(Long userId, OrderStatus status);
    Page<Order> findAllByUserId(Pageable pageableRequest, Long userId);
}