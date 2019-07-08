package io.zensoft.food.dto;

import io.zensoft.food.enums.OrderStatus;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Data
public class OrderDto {
    private Long id;

    private OrderStatus status;

    private List<OrderItemDto> items;

    private BigDecimal total;
}
