package io.zensoft.food.controller;

import io.zensoft.food.payload.UserIdentityAvailability;
import io.zensoft.food.payload.UserProfile;
import io.zensoft.food.payload.UserSummary;
import io.zensoft.food.repository.UserRepository;
import io.zensoft.food.security.CurrentUser;
import io.zensoft.food.security.UserPrincipal;
import io.zensoft.food.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MainController {

    private final UserServiceImpl userService;
    private final UserRepository userRepository;

    @Autowired
    public MainController(UserRepository userRepository,
                          UserServiceImpl userService){
        this.userRepository = userRepository;
        this.userService = userService;
    }

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('ROLE_USER')")
    public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        return new UserSummary(currentUser.getId(), currentUser.getUsername());
    }

    @GetMapping("/user/checkEmailAvailability")
    public UserIdentityAvailability checkEmailAvailability(@RequestParam(value = "email") String email) {
        return userService.existsByEmail(email);
    }

    @GetMapping("/user/{id}")
    public UserProfile getUserProfileById(@PathVariable(value = "id") long id) {
        return userService.findById(id);
    }
}
