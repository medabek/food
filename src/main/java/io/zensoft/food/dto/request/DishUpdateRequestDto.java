package io.zensoft.food.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


@Data
public class DishUpdateRequestDto {
    @NotBlank
    private String name;

    private double portion;

    @NotNull
    private BigDecimal price;
    
    @NotNull
    private Long cafeId;
    
   
}
