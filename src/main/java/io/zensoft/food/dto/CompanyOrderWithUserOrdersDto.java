package io.zensoft.food.dto;

import io.zensoft.food.enums.CompanyOrderStatus;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Data
@Builder
public class CompanyOrderWithUserOrdersDto {

    private Long id;

    private CompanyOrderStatus status;

    private List<OrderDto> orders;

    private Map<Long, BigDecimal> cafesTotal;
}

