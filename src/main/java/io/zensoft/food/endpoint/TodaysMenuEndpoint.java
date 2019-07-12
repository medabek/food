package io.zensoft.food.endpoint;

import io.zensoft.food.dto.TodaysMenuDto;
import io.zensoft.food.dto.request.TodaysMenuRequestDto;

public interface TodaysMenuEndpoint {
    TodaysMenuDto addDish(TodaysMenuRequestDto request);

    void removeDish(TodaysMenuRequestDto request);

    TodaysMenuDto relevant();

    TodaysMenuDto irrelevant();

    TodaysMenuDto getTodaysMenu();
}
