package io.zensoft.food.service.impl;

import io.zensoft.food.domain.TodaysMenuRequest;
import io.zensoft.food.enums.TodaysMenuStatus;
import io.zensoft.food.exception.LogicException;
import io.zensoft.food.model.Dish;
import io.zensoft.food.model.TodaysMenu;
import io.zensoft.food.repository.TodaysMenuRepository;
import io.zensoft.food.service.DishService;
import io.zensoft.food.service.TodaysMenuService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class TodaysMenuServiceImpl implements TodaysMenuService {

    private TodaysMenuRepository todaysMenuRepository;
    private DishService dishService;

    @Autowired
    public TodaysMenuServiceImpl(TodaysMenuRepository todaysMenuRepository,
                                 DishService dishService) {
        this.todaysMenuRepository = todaysMenuRepository;
        this.dishService = dishService;
    }

    @Transactional
    @Override
    public TodaysMenu addDish(@NonNull TodaysMenuRequest request) {

        TodaysMenu todaysMenu = getTodaysMenu();

        Dish dish = dishService.getById(request.getDishId());

        if (todaysMenu.getDishes().contains(dish)) {
            throw new LogicException("Dish already exists");
        }

        todaysMenu.addDish(dish);

        return todaysMenuRepository.save(todaysMenu);
    }

    @Transactional
    @Override
    public void removeDish(@NonNull TodaysMenuRequest request) {

        Dish dish = dishService.getById(request.getDishId());

        TodaysMenu todaysMenu = getTodaysMenu();

        if (!todaysMenu.getDishes().contains(dish)){
            throw new EntityNotFoundException("Dish not found");
        }

        todaysMenu.removeDish(dish);
        todaysMenuRepository.save(todaysMenu);
    }

    @Transactional
    @Override
    public TodaysMenu relevant() {

        TodaysMenu todaysMenu = todaysMenuRepository.findByStatus(TodaysMenuStatus.RELEVANT)
                .orElse( new TodaysMenu() );

        return todaysMenuRepository.save(todaysMenu);
    }

    @Transactional
    @Override
    public TodaysMenu irrelevant() {

        TodaysMenu todaysMenu = getTodaysMenu();

        todaysMenu.setStatus(TodaysMenuStatus.IRRELEVANT);

        return todaysMenuRepository.save(todaysMenu);
    }

    @Transactional(readOnly = true)
    @Override
    public TodaysMenu getTodaysMenu() {

        TodaysMenu todaysMenu = todaysMenuRepository.findByStatus(TodaysMenuStatus.RELEVANT)
                .orElseThrow(() -> new LogicException("Today's menu not found"));

        return todaysMenu;
    }
}
