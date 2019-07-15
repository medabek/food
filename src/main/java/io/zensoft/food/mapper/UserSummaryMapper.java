package io.zensoft.food.mapper;

import io.zensoft.food.model.User;
import io.zensoft.food.payload.UserSummary;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
public class UserSummaryMapper {
    public UserSummary toUserSummary(@NonNull User user){
        return UserSummary.builder()
                .firstname(user.getFirstname())
                .id(user.getId())
                .lastname(user.getLastname())
                .balance(user.getBalance())
                .build();
    }
}
