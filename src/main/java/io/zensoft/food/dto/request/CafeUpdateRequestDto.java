package io.zensoft.food.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CafeUpdateRequestDto {
    @NotBlank
    private String name;
}
