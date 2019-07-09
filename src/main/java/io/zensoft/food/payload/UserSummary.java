package io.zensoft.food.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Builder

@Getter
@Setter
@AllArgsConstructor
public class UserSummary {
    private Long id;
    private String firstname;
    private String lastname;
    private BigDecimal balance;
}
