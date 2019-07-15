package io.zensoft.food.service;

import io.zensoft.food.domain.SearchUserRequest;
import io.zensoft.food.model.User;
import io.zensoft.food.payload.*;
import io.zensoft.food.security.UserPrincipal;

import java.util.List;


public interface UserService {
    User signup(SignUpRequest signUpRequest);

    JwtAuthenticationResponse signin(LoginRequest loginRequest);

    UserIdentityAvailability existsByEmail(String email);

    UserProfile findById(long id);

    User currentUser(UserPrincipal currentUser);

    List<User> findByName(String search);
}