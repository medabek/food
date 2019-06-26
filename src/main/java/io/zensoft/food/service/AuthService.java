package io.zensoft.food.service;

import io.zensoft.food.payload.LoginRequest;
import org.springframework.security.core.Authentication;

public interface AuthService {
    Authentication getAuthentication(LoginRequest loginRequest);
}
