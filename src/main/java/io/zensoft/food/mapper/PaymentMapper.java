package io.zensoft.food.mapper;

import io.zensoft.food.dto.PaymentDto;
import io.zensoft.food.model.Payment;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {
    public PaymentDto toPaymentDto(@NonNull Payment payment){
        return PaymentDto.builder()
                .userId(payment.getUser().getId())
                .managerId(payment.getManager().getId())
                .amount(payment.getAmount())
                .time(payment.getTime())
                .build();
    }
}
