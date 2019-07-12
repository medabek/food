package io.zensoft.food.mapper;

import io.zensoft.food.dto.TodaysMenuDto;
import io.zensoft.food.model.TodaysMenu;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class TodaysMenuMapper {

    private DishMapper dishMapper;

    @Autowired
    public TodaysMenuMapper(DishMapper dishMapper) {
        this.dishMapper = dishMapper;
    }

    public TodaysMenuDto toTodaysMenuDto(@NonNull TodaysMenu todaysMenu){
        return TodaysMenuDto.builder()
                .id(todaysMenu.getId())
                .status(todaysMenu.getStatus())
                .time(todaysMenu.getTime())
                .dishes(todaysMenu.getDishes().stream()
                                    .map(dishMapper::toDishDto)
                                    .collect(Collectors.toList()))
                .build();
    }
}
