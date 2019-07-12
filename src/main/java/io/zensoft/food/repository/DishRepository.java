package io.zensoft.food.repository;

import io.zensoft.food.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DishRepository extends JpaRepository<Dish, Long> {

    Dish getById(Long id);

    Optional<Dish> findById(Long dishId);
}
