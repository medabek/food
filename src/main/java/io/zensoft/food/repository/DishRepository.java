package io.zensoft.food.repository;

import io.zensoft.food.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishRepository extends JpaRepository<Dish, Long> {

    Dish getById(Long id);

}
