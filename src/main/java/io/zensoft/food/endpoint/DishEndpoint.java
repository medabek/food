package io.zensoft.food.endpoint;

import io.zensoft.food.dto.DishDto;
import io.zensoft.food.dto.request.DishCreateRequestDto;
import io.zensoft.food.dto.request.DishUpdateRequestDto;

import java.util.List;

public interface DishEndpoint {
    DishDto add(DishCreateRequestDto request);

    List<DishDto> getAllDishesByCafeId(Long cafeId);

    DishDto update(Long id, DishUpdateRequestDto request);

    void delete(Long id);

    DishDto getById(Long id);
}
