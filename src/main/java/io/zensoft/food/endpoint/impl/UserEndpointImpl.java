package io.zensoft.food.endpoint.impl;

import io.zensoft.food.domain.SearchUserRequest;
import io.zensoft.food.dto.request.SearchUserRequestDto;
import io.zensoft.food.endpoint.UserEndpoint;
import io.zensoft.food.mapper.UserSummaryMapper;
import io.zensoft.food.model.User;
import io.zensoft.food.payload.UserSummary;
import io.zensoft.food.service.UserService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserEndpointImpl implements UserEndpoint {

    private UserService userService;
    private UserSummaryMapper userSummaryMapper;

    @Autowired
    public UserEndpointImpl(UserService userService,
                            UserSummaryMapper userSummaryMapper) {
        this.userService = userService;
        this.userSummaryMapper = userSummaryMapper;
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserSummary> getUserByName(@NonNull SearchUserRequestDto request) {

        List<User> users = userService.findByName(request.getSearch());

        return users.stream()
                .map(userSummaryMapper::toUserSummary)
                .collect(Collectors.toList());
    }
}
