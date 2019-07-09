package io.zensoft.food.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Data
public class ReplenishTheBalanceRequestDto {
    @NotNull
    private Long userId;

    @NotNull
    @Positive
    private BigDecimal sum;
}
