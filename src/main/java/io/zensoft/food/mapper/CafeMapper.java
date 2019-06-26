package io.zensoft.food.mapper;

import io.zensoft.food.dto.CafeDto;
import io.zensoft.food.model.Cafe;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
public class CafeMapper {
    public CafeDto toCafeDto(@NonNull Cafe cafe) {
        return CafeDto.builder()
                .id(cafe.getId())
                .name(cafe.getName())
                .build();
    }
}
