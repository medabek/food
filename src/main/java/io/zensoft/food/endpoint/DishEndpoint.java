package io.zensoft.food.endpoint;

import io.zensoft.food.dto.DishDto;
import io.zensoft.food.dto.request.DishCreateRequestDto;
import io.zensoft.food.dto.request.DishUpdateRequestDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DishEndpoint {
    DishDto add(DishCreateRequestDto request, MultipartFile multipartFile);

    List<DishDto> getAllDishesByCafeId(Long cafeId);

    DishDto update(Long id, DishUpdateRequestDto request, MultipartFile multipartFile);

    void delete(Long id);

    DishDto getById(Long id);
}
