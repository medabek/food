package io.zensoft.food.controller;

import io.zensoft.food.exception.ResourceNotFoundException;
import io.zensoft.food.model.User;
import io.zensoft.food.payload.UserIdentityAvailability;
import io.zensoft.food.payload.UserProfile;
import io.zensoft.food.payload.UserSummary;
import io.zensoft.food.repository.UserRepository;
import io.zensoft.food.security.CurrentUser;
import io.zensoft.food.security.UserPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MainController {

    private final UserRepository userRepository;

    @Autowired
    public MainController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        UserSummary userSummary = new UserSummary(currentUser.getId(), currentUser.getUsername());
        return userSummary;
    }

    @GetMapping("/user/checkUsernameAvailability")
    public UserIdentityAvailability checkUsernameAvailability(@RequestParam(value = "username") String username) {
        Boolean isAvailable = !userRepository.existsByUsername(username);
        return new UserIdentityAvailability(isAvailable);
    }

    @GetMapping("/user/checkEmailAvailability")
    public UserIdentityAvailability checkEmailAvailability(@RequestParam(value = "email") String email) {
        Boolean isAvailable = !userRepository.existsByEmail(email);
        return new UserIdentityAvailability(isAvailable);
    }

    @GetMapping("/users/{username}")
    public UserProfile getUserProfile(@PathVariable(value = "username") String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
        UserProfile userProfile = new UserProfile(user.getId(), user.getUsername(), user.getEmail());
        return userProfile;
    }

    @GetMapping("/user/{id}")
    public UserProfile getUserProfileById(@PathVariable(value = "id") long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        UserProfile userProfile = new UserProfile(user.getId(), user.getUsername(), user.getEmail());
        return userProfile;
    }

}
