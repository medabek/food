package io.zensoft.food.mapper;

import io.zensoft.food.dto.DishDto;
import io.zensoft.food.model.Dish;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
public class DishMapper {
    public DishDto toDishDto(@NonNull Dish dish) {
        return DishDto.builder()
                .id(dish.getId())
                .name(dish.getName())
                .portion(dish.getPortion())
                .price(dish.getPrice())
                .imageUrl(dish.getImageUrl())
                .build();
    }
}
