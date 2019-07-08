package io.zensoft.food.endpoint.impl;

import io.zensoft.food.domain.AddItemRequest;
import io.zensoft.food.dto.OrderDto;
import io.zensoft.food.dto.request.AddItemRequestDto;
import io.zensoft.food.endpoint.OrderEndpoint;
import io.zensoft.food.mapper.OrderMapper;
import io.zensoft.food.model.Order;
import io.zensoft.food.security.UserPrincipal;
import io.zensoft.food.service.OrderService;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderEndpointImpl implements OrderEndpoint {

    private OrderService orderService;
    private OrderMapper orderMapper;

    public OrderEndpointImpl(OrderService orderService,
                             OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @Transactional
    @Override
    public OrderDto addItem(@NonNull AddItemRequestDto request,
                            @NonNull UserPrincipal currentUser) {

        AddItemRequest addItemRequest = new AddItemRequest(
                request.getDishId(),
                request.getQuantity()
        );

        Order order = orderService.addItem(addItemRequest, currentUser);

        return orderMapper.toOrderDto(order);
    }

    @Transactional(readOnly = true)
    @Override
    public OrderDto getCurrentOrder(@NonNull UserPrincipal currentUser) {

        Order order = orderService.getUserCurrentOrder(currentUser).orElseThrow(() -> new EntityNotFoundException("Open order not found"));

        return orderMapper.toOrderDto(order);
    }

    @Transactional(readOnly = true)
    @Override
    public List<OrderDto> getOrdersByCurrentUser(@NonNull UserPrincipal currentUser) {

        List<Order> orders = orderService.getAllByUser(currentUser);

        return orders.stream()
                .map(orderMapper::toOrderDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public OrderDto confirm(@NonNull UserPrincipal currentUser) {

        Order order = orderService.confirmOrder(currentUser);

        return orderMapper.toOrderDto(order);
    }

    @Transactional
    @Override
    public void delete(@NonNull AddItemRequestDto request,
                       @NonNull UserPrincipal currentUser) {

        orderService.deleteItem(request, currentUser);

    }
}
