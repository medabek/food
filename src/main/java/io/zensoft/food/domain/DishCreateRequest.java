package io.zensoft.food.domain;

import io.zensoft.food.model.Cafe;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class DishCreateRequest {
    private String name;

    private double portion;

    private BigDecimal price;
    
    private Cafe cafe;
}
