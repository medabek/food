package io.zensoft.food.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class AddItemRequestDto {

    @NotNull
    private Long dishId;

    @NotNull
    @Positive
    private Integer quantity;
}
