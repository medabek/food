package io.zensoft.food.service;

import io.zensoft.food.domain.AddItemRequest;
import io.zensoft.food.dto.OrderPageDto;
import io.zensoft.food.dto.request.AddItemRequestDto;
import io.zensoft.food.model.Order;
import io.zensoft.food.security.CurrentUser;
import io.zensoft.food.security.UserPrincipal;
import lombok.NonNull;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface OrderService {

    Order addItem(@NonNull AddItemRequest request, @NonNull @CurrentUser UserPrincipal currentUser);

    Order confirmOrder(@CurrentUser UserPrincipal currentUser);

    Optional<Order> getUserCurrentOrder(@CurrentUser UserPrincipal currentUser);

    OrderPageDto getAllByUser(@CurrentUser UserPrincipal currentUser, Pageable pageableRequest);

    void deleteItem(@NonNull AddItemRequestDto request, @NonNull @CurrentUser UserPrincipal currentUser);
}
