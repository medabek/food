package io.zensoft.food.service;

import io.zensoft.food.domain.DishCreateRequest;
import io.zensoft.food.domain.DishUpdateRequest;
import io.zensoft.food.model.Dish;

import java.util.List;

public interface DishService {

    List<Dish> getAllByCafeId(Long cafeId);

    Dish getById(Long id);

    Dish update(DishUpdateRequest request);

    void delete(Long id);

    Dish add(DishCreateRequest request);
}
