package io.zensoft.food.repository;

import io.zensoft.food.enums.CompanyOrderStatus;
import io.zensoft.food.model.CompanyOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyOrderRepository extends JpaRepository<CompanyOrder, Long> {
    Optional<CompanyOrder> findByOrderStatus(CompanyOrderStatus orderStatus);
}
