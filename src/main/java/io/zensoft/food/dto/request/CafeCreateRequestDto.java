package io.zensoft.food.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class CafeCreateRequestDto {
    @NotBlank
    private String name;

    @NotNull
    private BigDecimal delivery;
}
