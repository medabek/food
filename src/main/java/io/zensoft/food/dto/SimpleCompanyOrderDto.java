package io.zensoft.food.dto;

import io.zensoft.food.enums.CompanyOrderStatus;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
public class SimpleCompanyOrderDto {

    private Long id;

    private CompanyOrderStatus status;

    private LocalDateTime openingTime;

    private LocalDateTime closingTime;

    private Long managerId;

    private Map<Long, BigDecimal> cafesTotal;
}
