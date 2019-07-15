package io.zensoft.food.controller;

import io.zensoft.food.dto.request.SearchUserRequestDto;
import io.zensoft.food.endpoint.UserEndpoint;
import io.zensoft.food.payload.UserSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private UserEndpoint userEndpoint;

    @Autowired
    public UserController(UserEndpoint userEndpoint) {
        this.userEndpoint = userEndpoint;
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<UserSummary>> getByName(@Valid SearchUserRequestDto request){
        return ResponseEntity.ok(userEndpoint.getUserByName(request));
    }
}
