package io.zensoft.food.service;

import io.zensoft.food.model.User;
import io.zensoft.food.payload.*;
import io.zensoft.food.security.UserPrincipal;


public interface UserService {
    User signup(SignUpRequest signUpRequest);

    JwtAuthenticationResponse signin(LoginRequest loginRequest);

    UserIdentityAvailability existsByEmail(String email);

    UserProfile findById(long id);

    User getUserByEmail(String email);

    User currentUser(UserPrincipal currentUser);
}