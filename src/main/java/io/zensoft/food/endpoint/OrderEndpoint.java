package io.zensoft.food.endpoint;

import io.zensoft.food.dto.OrderDto;
import io.zensoft.food.dto.OrderPageDto;
import io.zensoft.food.dto.request.AddItemRequestDto;
import io.zensoft.food.security.UserPrincipal;

public interface OrderEndpoint {

    OrderDto addItem(AddItemRequestDto request, UserPrincipal currentUser);

    OrderDto getCurrentOrder(UserPrincipal currentUser);

    OrderPageDto getOrdersByCurrentUser(UserPrincipal currentUser, int page, int limit);

    OrderDto confirm(UserPrincipal currentUser);

    void delete(AddItemRequestDto request, UserPrincipal currentUser);
}
