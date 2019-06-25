package io.zensoft.food.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSummary {
    private Long id;
    private String username;

    public UserSummary(Long id, String username) {
        this.id = id;
        this.username = username;
    }

}
