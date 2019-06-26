package io.zensoft.food.service;

import io.zensoft.food.domain.CafeCreateRequest;
import io.zensoft.food.domain.CafeUpdateRequest;
import io.zensoft.food.model.Cafe;

import java.util.List;

public interface CafeService {
    Cafe add(CafeCreateRequest request);

    Cafe update(CafeUpdateRequest request);

    List<Cafe> getAll();

    Cafe getById(Long id);

    void delete(Long id);
}
