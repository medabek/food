package io.zensoft.food.dto;

import io.zensoft.food.payload.UserSummary;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserBalanceDto {
    private UserSummary userSummary;
}
