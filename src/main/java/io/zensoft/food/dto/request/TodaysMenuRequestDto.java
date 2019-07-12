package io.zensoft.food.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class TodaysMenuRequestDto {
    @NotNull
    private Long dishId;
}
