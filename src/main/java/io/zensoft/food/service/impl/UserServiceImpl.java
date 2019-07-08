package io.zensoft.food.service.impl;

import io.zensoft.food.exception.LogicException;
import io.zensoft.food.exception.ResourceNotFoundException;
import io.zensoft.food.model.Role;
import io.zensoft.food.model.RoleName;
import io.zensoft.food.model.User;
import io.zensoft.food.payload.*;
import io.zensoft.food.repository.RoleRepository;
import io.zensoft.food.repository.UserRepository;
import io.zensoft.food.security.JwtTokenProvider;
import io.zensoft.food.security.UserPrincipal;
import io.zensoft.food.service.AuthService;
import io.zensoft.food.service.UserService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthService authService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder,
                           JwtTokenProvider jwtTokenProvider,
                           AuthService authService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authService = authService;
    }

    public User signup(@NonNull SignUpRequest signUpRequest) {

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new LogicException("User with this email exists.");
        }

        User user = new User(
                signUpRequest.getFirstname(),
                signUpRequest.getLastname(),
                signUpRequest.getEmail(),
                signUpRequest.getPassword()
        );

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new LogicException("User Role not set."));

        user.setRoles(Collections.singleton(userRole));

        return userRepository.save(user);
    }

    public JwtAuthenticationResponse signin(@NonNull LoginRequest loginRequest) {
        Authentication authentication = authService.getAuthentication(loginRequest);

        User user = userRepository.findUserByEmail(loginRequest.getEmail());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtTokenProvider.generateToken(authentication);

        Set<Role> role = user.getRoles();

        return new JwtAuthenticationResponse(jwt, role);
    }

    public UserIdentityAvailability existsByEmail(String email) {
        Boolean isAvailable = !userRepository.existsByEmail(email);
        return new UserIdentityAvailability(isAvailable);
    }

    public UserProfile findById(long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        return new UserProfile(user.getId(), user.getFirstname(), user.getLastname(), user.getEmail());
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public User currentUser(UserPrincipal currentUser) {
        return userRepository.findUserByEmail(currentUser.getEmail());
    }
}
