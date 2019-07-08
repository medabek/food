package io.zensoft.food.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class OrderItemDto {

    private String dishName;

    private BigDecimal price;

    private Integer quantity;

    private BigDecimal total;
}
