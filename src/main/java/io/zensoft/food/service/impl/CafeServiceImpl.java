package io.zensoft.food.service.impl;

import io.zensoft.food.domain.CafeCreateRequest;
import io.zensoft.food.domain.CafeUpdateRequest;
import io.zensoft.food.model.Cafe;
import io.zensoft.food.repository.CafeRepository;
import io.zensoft.food.service.CafeService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CafeServiceImpl implements CafeService {

    private CafeRepository cafeRepository;

    @Autowired
    public CafeServiceImpl(CafeRepository cafeRepository) {
        this.cafeRepository = cafeRepository;
    }

    @Transactional
    @Override
    public Cafe add(@NonNull CafeCreateRequest request) {

        Cafe cafe = new Cafe();
        cafe.setName(request.getName());
        cafe.setDelivery(request.getDelivery());
        cafe.setLogoName(request.getLogoName());

        return cafeRepository.save(cafe);
    }

    @Transactional
    @Override
    public Cafe update(@NonNull CafeUpdateRequest request) {

        if (!cafeRepository.existsById(request.getId())) {
            throw new EntityNotFoundException("Cafe with id = " + request.getId() + " not found.");
        }

        Cafe cafe = new Cafe();

        cafe.setId(request.getId());
        cafe.setName(request.getName());
        cafe.setDelivery(request.getDelivery());
        cafe.setLogoName(request.getLogoName());

        return cafeRepository.save(cafe);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Cafe> getAll() {
        return cafeRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Cafe getById(@NonNull Long id) {
        return cafeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cafe with id = " + id + " not found"));
    }

    @Transactional
    @Override
    public void delete(@NonNull Long id) {

        Cafe cafe = cafeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cafe with id = " + id + " not found"));

        cafeRepository.deleteById(id);
    }
}
