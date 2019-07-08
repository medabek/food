package io.zensoft.food.dto;

import io.zensoft.food.enums.CompanyOrderStatus;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class SimpleCompanyOrderDto {

    private Long id;

    private CompanyOrderStatus status;

    private LocalDateTime openingTime;

    private LocalDateTime closingTime;

    private Long managerId;

    private BigDecimal total;
}
