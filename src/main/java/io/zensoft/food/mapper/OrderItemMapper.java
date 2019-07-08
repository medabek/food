package io.zensoft.food.mapper;

import io.zensoft.food.dto.OrderItemDto;
import io.zensoft.food.model.OrderItem;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
public class OrderItemMapper {
    public OrderItemDto toItemDto(@NonNull OrderItem item){
        return OrderItemDto.builder()
                .dishName(item.getDishName())
                .price(item.getPrice())
                .quantity(item.getQuantity())
                .total(item.getTotal())
                .build();
    }
}
