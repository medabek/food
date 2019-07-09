package io.zensoft.food.service;

import io.zensoft.food.domain.ReplenishTheBalanceRequest;
import io.zensoft.food.model.*;
import io.zensoft.food.security.UserPrincipal;

import java.util.List;


public interface PaymentService {

    Payment addUserBalance(ReplenishTheBalanceRequest request, UserPrincipal currentUser);

    List<Payment> getPaymentsByUserId(Long userId);
}
