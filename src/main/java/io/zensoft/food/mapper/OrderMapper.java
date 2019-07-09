package io.zensoft.food.mapper;

import io.zensoft.food.dto.OrderDto;
import io.zensoft.food.model.Order;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class OrderMapper {

    private final OrderItemMapper itemMapper;

    @Autowired
    public OrderMapper(OrderItemMapper itemMapper) {
        this.itemMapper = itemMapper;
    }

    public OrderDto toOrderDto(@NonNull Order order){
        return OrderDto.builder()
                .id(order.getId())
                .status(order.getStatus())
                .items(order.getItems().stream().
                        map(itemMapper::toItemDto).
                        collect(Collectors.toList()))
                .total(order.getTotal())
                .userId(order.getUserId())
                .build();
    }
}
