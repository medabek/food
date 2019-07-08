package io.zensoft.food.dto;

import io.zensoft.food.enums.CompanyOrderStatus;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class CompanyOrderWithUserOrdersDto {

    private Long id;

    private CompanyOrderStatus status;

    private List<OrderDto> orders;

    private BigDecimal total;
}

