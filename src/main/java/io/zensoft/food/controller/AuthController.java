package io.zensoft.food.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.zensoft.food.model.User;
import io.zensoft.food.payload.JwtAuthenticationResponse;
import io.zensoft.food.payload.LoginRequest;
import io.zensoft.food.payload.SignUpRequest;
import io.zensoft.food.security.JwtTokenProvider;
import io.zensoft.food.service.impl.AuthServiceImpl;
import io.zensoft.food.service.impl.UserServiceImpl;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@Api(value = "ZenFood", description = "Operations to login and register to ZenFood Management System")
public class AuthController {

    private UserServiceImpl userService;

    @Autowired
    public AuthController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "Operation to signIn")
    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        JwtAuthenticationResponse response = userService.signin(loginRequest);
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "Operation to signUp")
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        User user = userService.signup(signUpRequest);
        return ResponseEntity.ok(user);
    }
}
