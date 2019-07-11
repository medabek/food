package io.zensoft.food.service.impl;

import io.zensoft.food.domain.AddItemRequest;
import io.zensoft.food.dto.request.AddItemRequestDto;
import io.zensoft.food.enums.OrderStatus;
import io.zensoft.food.exception.LogicException;
import io.zensoft.food.model.*;
import io.zensoft.food.repository.*;
import io.zensoft.food.security.UserPrincipal;
import io.zensoft.food.service.*;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private OrderItemRepository orderItemRepository;
    private CompanyOrderService companyOrderService;
    private DishService dishService;
    private UserService userService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository,
                            OrderItemRepository orderItemRepository,
                            CompanyOrderService companyOrderService,
                            DishService dishService,
                            UserService userService) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.companyOrderService = companyOrderService;
        this.dishService = dishService;
        this.userService = userService;
    }


    @Transactional
    @Override
    public Order addItem(@NonNull AddItemRequest request,
                         @NonNull UserPrincipal currentUser) {

        Dish dish = dishService.getById(request.getDishId());

        Order order = getUserCurrentOrder(currentUser).orElseGet(() -> {
            Order newOrder = new Order();

            newOrder.setStatus(OrderStatus.OPEN);
            newOrder.setUserId(currentUser.getId());

            return orderRepository.save(newOrder);

        });

        Optional<OrderItem> orderItemOptional = orderItemRepository.findByOrderAndDishId(order, request.getDishId());

        OrderItem orderItem = orderItemOptional.orElseGet(() -> {
            OrderItem item = new OrderItem();

            item.setDish(dish);
            item.setDishName(dish.getName());
            item.setPrice(dish.getPrice());
            item.setQuantity(request.getQuantity());

            order.addItem(item);
            return item;

        });

        orderItem.setQuantity(request.getQuantity());
        orderItemRepository.save(orderItem);

        return orderRepository.save(order);
    }

    @Transactional
    @Override
    public Order confirmOrder(@NonNull UserPrincipal currentUser) {

        CompanyOrder companyOrder = companyOrderService.currentCompanyOrder();

        Order order = getUserCurrentOrder(currentUser)
                .orElseThrow(() -> new LogicException("Open order not found"));

        User user = userService.currentUser(currentUser);

        if (user == null) {
            throw new EntityNotFoundException("User not found");
        }

        if (user.getBalance().compareTo(order.getTotal()) == -1) {
            throw new LogicException("You do not have enough money");
        }

        user.offBalance(order.getTotal());

        order.setStatus(OrderStatus.CLOSE);
        order.setDate(LocalDateTime.now());
        order.setCompanyOrder(companyOrder);

        return orderRepository.save(order);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Order> getUserCurrentOrder(@NonNull UserPrincipal currentUser) {

        Long userId = currentUser.getId();

        return orderRepository.findByUserIdAndStatus(userId, OrderStatus.OPEN);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Order> getAllByUser(@NonNull UserPrincipal currentUser, Pageable pageableRequest) {

        Long userId = currentUser.getId();
        Page<Order> allOrders = orderRepository.findAllByUserId(pageableRequest, userId);
        List<Order> orderEntities = allOrders.getContent();

        return orderEntities;
    }

    @Transactional
    @Override
    public void deleteItem(@NonNull AddItemRequestDto request,
                           @NonNull UserPrincipal currentUser) {

        Order order = getUserCurrentOrder(currentUser)
                .orElseThrow(() -> new EntityNotFoundException("Open order not found"));

        OrderItem item = orderItemRepository.findByOrderAndDishId(order, request.getDishId())
                .orElseThrow(() -> new EntityNotFoundException("Item not found"));

        int diff = item.getQuantity() - request.getQuantity();

        if (diff < 0) {
            throw new LogicException("Quantity can not be negative");
        }

        if (diff == 0) {
            order.getItems().removeIf(orderItem -> orderItem.getId().equals(item.getId()));
            order.setTotal(order.getTotal());
            return;
        }

        item.setQuantity(diff);
        order.setTotal(order.getTotal());
        orderRepository.save(order);
    }
}