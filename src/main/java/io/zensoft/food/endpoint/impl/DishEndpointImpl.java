package io.zensoft.food.endpoint.impl;

import io.zensoft.food.domain.DishCreateRequest;
import io.zensoft.food.domain.DishUpdateRequest;
import io.zensoft.food.dto.DishDto;
import io.zensoft.food.dto.request.DishCreateRequestDto;
import io.zensoft.food.dto.request.DishUpdateRequestDto;
import io.zensoft.food.endpoint.DishEndpoint;
import io.zensoft.food.mapper.DishMapper;
import io.zensoft.food.model.Cafe;
import io.zensoft.food.model.Dish;
import io.zensoft.food.service.CafeService;
import io.zensoft.food.service.DishService;
import io.zensoft.food.service.impl.AmazonClientServiceImpl;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DishEndpointImpl implements DishEndpoint {

    private final CafeService cafeService;
    private final DishService dishService;
    private final DishMapper dishMapper;
    private final AmazonClientServiceImpl amazonClientService;

    @Autowired
    public DishEndpointImpl(CafeService cafeService,
                            AmazonClientServiceImpl amazonClientService,
                            DishService dishService, DishMapper dishMapper) {
        this.cafeService = cafeService;
        this.dishService = dishService;
        this.dishMapper = dishMapper;
        this.amazonClientService = amazonClientService;
    }

    @Transactional
    @Override
    public DishDto add(@NonNull DishCreateRequestDto request, MultipartFile multipartFile) {
        Cafe cafe = cafeService.getById(request.getCafeId());
        DishCreateRequest dishCreateRequest = new DishCreateRequest(
                request.getName(),
                request.getPortion(),
                request.getPrice(),
                cafe                
        );

        Dish dish = dishService.add(dishCreateRequest, multipartFile);

        return dishMapper.toDishDto(dish);
    }

    @Transactional
    @Override
    public DishDto update(@NonNull Long id, @NonNull DishUpdateRequestDto request, MultipartFile multipartFile) {
        Cafe cafe = cafeService.getById(request.getCafeId());

        DishUpdateRequest dishUpdateRequest = new DishUpdateRequest(
                id,
                request.getName(),
                request.getPortion(),
                request.getPrice(),
                cafe
        );

        Dish updateDish = dishService.update(dishUpdateRequest, multipartFile);

        return dishMapper.toDishDto(updateDish);
    }

    @Transactional(readOnly = true)
    @Override
    public List<DishDto> getAllDishesByCafeId(@NonNull Long cafeId) {
        Cafe cafe = cafeService.getById(cafeId);

        return cafe.getDishes().stream()
                .map(dishMapper::toDishDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void delete(@NonNull Long id) {
        dishService.delete(id);
    }

    @Transactional(readOnly = true)
    @Override
    public DishDto getById(@NonNull Long id) {
        return dishMapper.toDishDto(dishService.getById(id));
    }
}
