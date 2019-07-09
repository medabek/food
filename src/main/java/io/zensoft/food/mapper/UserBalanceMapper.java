package io.zensoft.food.mapper;

import io.zensoft.food.dto.UserBalanceDto;
import io.zensoft.food.model.User;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserBalanceMapper {
    private UserSummaryMapper userSummaryMapper;

    @Autowired
    public UserBalanceMapper(UserSummaryMapper userSummaryMapper) {
        this.userSummaryMapper = userSummaryMapper;
    }

    public UserBalanceDto toUserBalanceDto(@NonNull User user){
        return UserBalanceDto.builder()
                .userSummary(userSummaryMapper.toUserSummary(user))
                .build();
    }
}
