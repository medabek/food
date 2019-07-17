package io.zensoft.food.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class CafeUpdateRequest {
    private Long id;
    private String name;
    private BigDecimal delivery;
    private String logoName;
}
