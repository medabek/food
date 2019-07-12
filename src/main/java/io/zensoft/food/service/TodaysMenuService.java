package io.zensoft.food.service;

import io.zensoft.food.domain.TodaysMenuRequest;
import io.zensoft.food.model.TodaysMenu;

public interface TodaysMenuService {
    TodaysMenu addDish(TodaysMenuRequest request);

    void removeDish(TodaysMenuRequest request);

    TodaysMenu relevant();

    TodaysMenu irrelevant();

    TodaysMenu getTodaysMenu();
}
