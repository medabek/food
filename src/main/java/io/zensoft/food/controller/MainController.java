package io.zensoft.food.controller;

import io.swagger.annotations.*;
import io.zensoft.food.payload.UserIdentityAvailability;
import io.zensoft.food.payload.UserProfile;
import io.zensoft.food.payload.UserSummary;
import io.zensoft.food.security.CurrentUser;
import io.zensoft.food.security.UserPrincipal;
import io.zensoft.food.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
@Api(value = "ZenFood", description = "Operations to get all informations about  users of ZenFood Management System")
public class MainController {

    private final UserServiceImpl userService;

    @Autowired
    public MainController(UserServiceImpl userService){
        this.userService = userService;
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")

    })

    @GetMapping("/user/checkEmailAvailability")
    @ApiOperation(value = "Check an email of user for availability")
    public UserIdentityAvailability checkEmailAvailability(@RequestParam(value = "email") String email) {
        return userService.existsByEmail(email);
    }

    @ApiOperation(value = "Get a user information by its Id")
    @GetMapping("/user/{id}")
    public UserProfile getUserProfileById(@PathVariable(value = "id") long id) {
        return userService.findById(id);
    }

    @GetMapping("/user/me")
    @PreAuthorize("ROLE_USER")
    @ApiOperation(value = "Current user body to see")
    public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        return new UserSummary(currentUser.getId(), currentUser.getFirstname(), currentUser.getLastname());
    }
}
