package io.zensoft.food.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class ReplenishTheBalanceRequest {
    @NotNull
    private Long userId;

    @Positive
    private BigDecimal amount;

}
