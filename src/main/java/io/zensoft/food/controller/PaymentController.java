package io.zensoft.food.controller;

import io.zensoft.food.dto.PaymentDto;
import io.zensoft.food.dto.request.ReplenishTheBalanceRequestDto;
import io.zensoft.food.endpoint.PaymentEndpoint;

import io.zensoft.food.security.CurrentUser;
import io.zensoft.food.security.UserPrincipal;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private PaymentEndpoint paymentEndpoint;

    @Autowired
    public PaymentController(PaymentEndpoint paymentEndpoint) {
        this.paymentEndpoint = paymentEndpoint;
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/replenish")
    public ResponseEntity<PaymentDto> addBalance(@Valid @RequestBody ReplenishTheBalanceRequestDto request,
                                                 @CurrentUser @NonNull UserPrincipal currentUser){
        return ResponseEntity.ok(paymentEndpoint.addBalance(request, currentUser));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/get-my-payments")
    public ResponseEntity<List<PaymentDto>> getPaymentsByCurrentUser(@NonNull @CurrentUser UserPrincipal currentUser){
        return ResponseEntity.ok(paymentEndpoint.getPaymentsByUserId(currentUser.getId()));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("get-user-payments")
    public ResponseEntity<List<PaymentDto>> getPaymentsByUserId(@RequestParam("userId") @NotNull Long userId){
        return ResponseEntity.ok(paymentEndpoint.getPaymentsByUserId(userId));
    }
}
