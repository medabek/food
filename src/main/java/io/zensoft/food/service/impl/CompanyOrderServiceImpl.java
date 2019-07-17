package io.zensoft.food.service.impl;

import io.zensoft.food.enums.CompanyOrderStatus;
import io.zensoft.food.exception.LogicException;
import io.zensoft.food.model.CompanyOrder;
import io.zensoft.food.model.Order;
import io.zensoft.food.model.OrderItem;
import io.zensoft.food.repository.CompanyOrderRepository;
import io.zensoft.food.security.UserPrincipal;
import io.zensoft.food.service.CompanyOrderService;
import io.zensoft.food.service.UserService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CompanyOrderServiceImpl implements CompanyOrderService {

    private CompanyOrderRepository companyOrderRepository;
    private UserService userService;
    private CafeServiceImpl cafeService;

    @Autowired
    public CompanyOrderServiceImpl(CompanyOrderRepository companyOrderRepository,
                                   UserService userService,
                                   CafeServiceImpl cafeService) {
        this.companyOrderRepository = companyOrderRepository;
        this.userService = userService;
        this.cafeService = cafeService;
    }


    @Transactional
    @Override
    public CompanyOrder openOrder(@NonNull UserPrincipal currentUser) {

        Optional<CompanyOrder> orderOptional = companyOrderRepository.findByOrderStatus(CompanyOrderStatus.OPEN);

        if (!orderOptional.isPresent()) {

            CompanyOrder newCompanyOrder = new CompanyOrder();

            newCompanyOrder.setOrderStatus(CompanyOrderStatus.OPEN);
            newCompanyOrder.setOpeningTime(LocalDateTime.now());
            newCompanyOrder.setManager(userService.currentUser(currentUser));

            return companyOrderRepository.save(newCompanyOrder);

        } else {
            throw new LogicException("First close previous order");
        }
    }

    @Transactional
    @Override
    public CompanyOrder closeOrder(UserPrincipal currentUser) {

        CompanyOrder companyOrder = currentCompanyOrder();
        companyOrder.setOrderStatus(CompanyOrderStatus.CLOSE);
        companyOrder.setClosingTime((LocalDateTime.now()));

        return companyOrderRepository.save(companyOrder);
    }

    @Transactional(readOnly = true)
    @Override
    public List<CompanyOrder> getAllOrders() {
        return companyOrderRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public CompanyOrder currentCompanyOrder() {
        return companyOrderRepository.findByOrderStatus(CompanyOrderStatus.OPEN)
                .orElseThrow(() -> new LogicException("There is no open company order"));
    }

    public Map<Long, BigDecimal> getCafesTotal(CompanyOrder companyOrder) {

        HashMap<Long, BigDecimal> cafesTotal = new HashMap<>();

        for (Order order : companyOrder.getOrders()) {

            for (OrderItem orderItem : order.getItems()) {

                Long id = orderItem.getDish().getCafe().getId();

                cafesTotal.putIfAbsent(id, BigDecimal.ZERO);

                cafesTotal.put(id, cafesTotal.get(id).add(orderItem.getTotal()));
            }

        }
        for (Long id : cafesTotal.keySet()) {
            cafesTotal.put(id, cafesTotal.get(id).add(cafeService.getById(id).getDelivery()));
        }

        return cafesTotal;
    }
}
