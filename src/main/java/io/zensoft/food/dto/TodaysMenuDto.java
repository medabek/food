package io.zensoft.food.dto;

import io.zensoft.food.enums.TodaysMenuStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
public class TodaysMenuDto {
    private Long id;

    private TodaysMenuStatus status;

    private LocalDateTime time;

    private List<DishDto> dishes;
}
