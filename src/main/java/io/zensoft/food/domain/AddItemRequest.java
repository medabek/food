package io.zensoft.food.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class AddItemRequest {

    private Long dishId;

    private Integer quantity;
}
