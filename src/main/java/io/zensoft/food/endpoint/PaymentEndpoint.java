package io.zensoft.food.endpoint;

import io.zensoft.food.dto.PaymentDto;
import io.zensoft.food.dto.request.ReplenishTheBalanceRequestDto;
import io.zensoft.food.security.UserPrincipal;

import java.util.List;

public interface PaymentEndpoint {

    PaymentDto addBalance(ReplenishTheBalanceRequestDto request, UserPrincipal currentUser);

    List<PaymentDto> getPaymentsByUserId(Long userId);
}
