package io.zensoft.food.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProfile {
    private Long id;
    private String username;
    private String email;

    public UserProfile(Long id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }
}
