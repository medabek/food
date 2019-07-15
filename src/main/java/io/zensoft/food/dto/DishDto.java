package io.zensoft.food.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class DishDto {
    private Long id;

    private String name;

    private double portion;

    private BigDecimal price;

    private String imageUrl;

    private Long cafeId;
}