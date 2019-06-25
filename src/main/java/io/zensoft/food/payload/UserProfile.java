package io.zensoft.food.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProfile {
    private Long id;
    private String username;
    private String email;
    private Integer balance;

    public UserProfile(Long id, String username, String email, Integer balance) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.balance = balance;
    }
}
