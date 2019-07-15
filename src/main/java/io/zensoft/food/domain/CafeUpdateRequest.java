package io.zensoft.food.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CafeUpdateRequest {
    private Long id;
    private String name;
    private String logoName;
}
