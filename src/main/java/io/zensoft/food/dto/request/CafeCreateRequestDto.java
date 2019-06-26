package io.zensoft.food.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CafeCreateRequestDto {
    @NotBlank
    private String name;
}
