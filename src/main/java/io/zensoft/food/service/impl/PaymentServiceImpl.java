package io.zensoft.food.service.impl;

import io.zensoft.food.domain.ReplenishTheBalanceRequest;
import io.zensoft.food.model.*;
import io.zensoft.food.repository.PaymentRepository;
import io.zensoft.food.repository.UserRepository;
import io.zensoft.food.security.UserPrincipal;
import io.zensoft.food.service.*;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {

    private UserService userService;
    private PaymentRepository paymentRepository;
    private UserRepository userRepository;

    @Autowired
    public PaymentServiceImpl(UserService userService,
                              PaymentRepository paymentRepository,
                              UserRepository userRepository) {
        this.userService = userService;
        this.paymentRepository = paymentRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public Payment addUserBalance(@NonNull ReplenishTheBalanceRequest request,
                                  @NonNull UserPrincipal currentUser) {

        Optional<User> optionalUser = userRepository.findById(request.getUserId());
        User user = optionalUser.orElseThrow(() ->
                new EntityNotFoundException("User with id = " + request.getUserId() + " not found"));

        User manager = userService.currentUser(currentUser);

        if (manager == null){
            throw new EntityNotFoundException("User not found");
        }

        Payment payment = new Payment();

        payment.setUser(user);
        payment.setManager(manager);
        payment.setAmount(request.getAmount());
        payment.setTime(LocalDateTime.now());

        user.addBalance(request.getAmount());

        return paymentRepository.save(payment);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Payment> getPaymentsByUserId(@NotNull Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User with id = " + userId + " not found"));

        return paymentRepository.findAllByUser(user);
    }
}
