package io.zensoft.food.service;

import io.zensoft.food.model.CompanyOrder;
import io.zensoft.food.security.UserPrincipal;

import java.util.List;

public interface CompanyOrderService {
    CompanyOrder openOrder(UserPrincipal currentUser);

    CompanyOrder closeOrder(UserPrincipal currentUser);

    List<CompanyOrder> getAllOrders();

    CompanyOrder currentCompanyOrder();
}
