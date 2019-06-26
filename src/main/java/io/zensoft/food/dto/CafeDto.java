package io.zensoft.food.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CafeDto {
    private Long id;
    private String name;
}
