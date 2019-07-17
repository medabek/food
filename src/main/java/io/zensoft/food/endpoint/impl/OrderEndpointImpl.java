package io.zensoft.food.endpoint.impl;

import io.zensoft.food.domain.AddItemRequest;
import io.zensoft.food.dto.GeneralPageDto;
import io.zensoft.food.dto.OrderDto;
import io.zensoft.food.dto.request.AddItemRequestDto;
import io.zensoft.food.endpoint.OrderEndpoint;
import io.zensoft.food.mapper.OrderMapper;
import io.zensoft.food.model.Order;
import io.zensoft.food.repository.OrderRepository;
import io.zensoft.food.security.UserPrincipal;
import io.zensoft.food.service.OrderService;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.stream.Collectors;

@Service
public class OrderEndpointImpl implements OrderEndpoint {

    private OrderService orderService;
    private OrderMapper orderMapper;
    private OrderRepository orderRepository;

    public OrderEndpointImpl(OrderService orderService,
                             OrderMapper orderMapper,
                             OrderRepository orderRepository) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
        this.orderRepository = orderRepository;
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
    public GeneralPageDto getOrdersByCurrentUser(@NonNull UserPrincipal currentUser, int page, int limit) {

        Pageable pageableRequest = PageRequest.of(page, limit);

        Page<Order> allOrders = orderRepository.findAllByUserId(pageableRequest, currentUser.getId());
        
        return new GeneralPageDto(allOrders.getTotalElements(),
                allOrders.getTotalPages(),
                allOrders.getContent().stream()
                        .map(orderMapper::toOrderDto)
                        .collect(Collectors.toList()));
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
