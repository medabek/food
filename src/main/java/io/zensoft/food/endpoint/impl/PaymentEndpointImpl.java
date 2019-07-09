package io.zensoft.food.endpoint.impl;

import io.zensoft.food.domain.ReplenishTheBalanceRequest;
import io.zensoft.food.dto.PaymentDto;
import io.zensoft.food.dto.request.ReplenishTheBalanceRequestDto;
import io.zensoft.food.endpoint.PaymentEndpoint;
import io.zensoft.food.mapper.PaymentMapper;
import io.zensoft.food.model.Payment;
import io.zensoft.food.security.UserPrincipal;
import io.zensoft.food.service.PaymentService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentEndpointImpl implements PaymentEndpoint {

    private PaymentService paymentService;
    private PaymentMapper paymentMapper;

    @Autowired
    public PaymentEndpointImpl(PaymentService paymentService,
                               PaymentMapper paymentMapper) {
        this.paymentService = paymentService;
        this.paymentMapper = paymentMapper;
    }

    @Transactional
    @Override
    public PaymentDto addBalance(@NonNull ReplenishTheBalanceRequestDto request,
                                 @NonNull UserPrincipal currentUser) {

        ReplenishTheBalanceRequest replenishTheBalanceRequest = new ReplenishTheBalanceRequest(
                request.getUserId(),
                request.getSum()
        );

        Payment payment = paymentService.addUserBalance(replenishTheBalanceRequest, currentUser);

        return paymentMapper.toPaymentDto(payment);
    }

    @Transactional(readOnly = true)
    @Override
    public List<PaymentDto> getPaymentsByUserId(@NotNull Long userId) {

        List<Payment> payments = paymentService.getPaymentsByUserId(userId);

        return payments.stream()
                .map(paymentMapper::toPaymentDto)
                .collect(Collectors.toList());
    }
}
