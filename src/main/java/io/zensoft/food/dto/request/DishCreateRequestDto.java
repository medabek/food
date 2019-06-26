package io.zensoft.food.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Data
public class DishCreateRequestDto {

    @NotBlank
    private String name;

    @NotNull
    @Positive
    private double portion;

    @NotNull
    @Positive
    private BigDecimal price;

    @NotNull
    private Long cafeId;
}
