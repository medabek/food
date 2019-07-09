package io.zensoft.food.repository;

import io.zensoft.food.model.Payment;
import io.zensoft.food.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findAllByUser(User user);
}
