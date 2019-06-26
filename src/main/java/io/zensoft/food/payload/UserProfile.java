package io.zensoft.food.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserProfile {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
}
