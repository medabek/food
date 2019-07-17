package io.zensoft.food.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CafeDto {
    private Long id;
    private String name;
    private BigDecimal delivery;

    private String logoUrl;
}
