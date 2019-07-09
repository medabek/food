package io.zensoft.food.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class PaymentDto {
    private Long userId;

    private Long managerId;

    private BigDecimal amount;

    private LocalDateTime time;
}
