package io.zensoft.food.service;

import io.zensoft.food.exception.AppException;
import io.zensoft.food.model.Role;
import io.zensoft.food.model.RoleName;
import io.zensoft.food.model.User;
import io.zensoft.food.payload.LoginRequest;
import io.zensoft.food.payload.SignUpRequest;
import io.zensoft.food.repository.RoleRepository;
import io.zensoft.food.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;


@Service("userService")
@Transactional
public class UserService{

    @Qualifier("userRepository")
    private final UserRepository userRepository;

    @Qualifier("roleRepository")
    private final RoleRepository roleRepository;

    private final AuthenticationManager authenticationManager;

    @Autowired
    public PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, AuthenticationManager authenticationManager){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.authenticationManager = authenticationManager;
    }

    public User saveUser(SignUpRequest signUpRequest){
    // Creating user's account
    User user = new User(signUpRequest.getUsername(),
            signUpRequest.getEmail(), signUpRequest.getPassword());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

    Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
            .orElseThrow(() -> new AppException("User Role not set."));

        user.setRoles(Collections.singleton(userRole));

    User result = userRepository.save(user);
    return result;

    }

    public Authentication getAuthentication(LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );
        return authentication;
    }

}