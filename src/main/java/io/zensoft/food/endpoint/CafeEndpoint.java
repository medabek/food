package io.zensoft.food.endpoint;

import io.zensoft.food.dto.request.CafeCreateRequestDto;
import io.zensoft.food.dto.CafeDto;
import io.zensoft.food.dto.request.CafeUpdateRequestDto;

import java.util.List;

public interface CafeEndpoint {
    CafeDto save(CafeCreateRequestDto request);

    CafeDto update(Long id, CafeUpdateRequestDto request);

    void delete(Long id);

    List<CafeDto> getAll();

    CafeDto getById(Long id);
}
