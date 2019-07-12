package io.zensoft.food.endpoint.impl;

import io.zensoft.food.domain.TodaysMenuRequest;
import io.zensoft.food.dto.TodaysMenuDto;
import io.zensoft.food.dto.request.TodaysMenuRequestDto;
import io.zensoft.food.endpoint.TodaysMenuEndpoint;
import io.zensoft.food.mapper.TodaysMenuMapper;
import io.zensoft.food.model.TodaysMenu;
import io.zensoft.food.service.TodaysMenuService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TodaysMenuEndpointImpl implements TodaysMenuEndpoint {

    private TodaysMenuService todaysMenuService;
    private TodaysMenuMapper todaysMenuMapper;

    @Autowired
    public TodaysMenuEndpointImpl(TodaysMenuService todaysMenuService,
                                  TodaysMenuMapper todaysMenuMapper) {
        this.todaysMenuService = todaysMenuService;
        this.todaysMenuMapper = todaysMenuMapper;
    }

    @Transactional
    @Override
    public TodaysMenuDto addDish(@NonNull TodaysMenuRequestDto request) {

        TodaysMenuRequest todaysMenuRequest = new TodaysMenuRequest(request.getDishId());

        TodaysMenu todaysMenu = todaysMenuService.addDish(todaysMenuRequest);

        return todaysMenuMapper.toTodaysMenuDto(todaysMenu);
    }

    @Transactional
    @Override
    public void removeDish(@NonNull TodaysMenuRequestDto request) {

        TodaysMenuRequest todaysMenuRequest = new TodaysMenuRequest(request.getDishId());

        todaysMenuService.removeDish(todaysMenuRequest);
    }

    @Transactional
    @Override
    public TodaysMenuDto relevant() {

        TodaysMenu todaysMenu = todaysMenuService.relevant();

        return todaysMenuMapper.toTodaysMenuDto(todaysMenu);
    }

    @Transactional
    @Override
    public TodaysMenuDto irrelevant() {

        TodaysMenu todaysMenu = todaysMenuService.irrelevant();

        return todaysMenuMapper.toTodaysMenuDto(todaysMenu);
    }

    @Transactional(readOnly = true)
    @Override
    public TodaysMenuDto getTodaysMenu() {

        TodaysMenu todaysMenu = todaysMenuService.getTodaysMenu();

        return todaysMenuMapper.toTodaysMenuDto(todaysMenu);
    }
}
