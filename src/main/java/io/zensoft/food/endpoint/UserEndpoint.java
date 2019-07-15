package io.zensoft.food.endpoint;

import io.zensoft.food.dto.request.SearchUserRequestDto;
import io.zensoft.food.payload.UserSummary;

import java.util.List;

public interface UserEndpoint {
    List<UserSummary> getUserByName(SearchUserRequestDto request);
}
