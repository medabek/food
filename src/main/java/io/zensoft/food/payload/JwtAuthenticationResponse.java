package io.zensoft.food.payload;

import io.zensoft.food.model.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class JwtAuthenticationResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private Set<Role> userRole;

    public JwtAuthenticationResponse(String accessToken, Set<Role> userRole) {
        this.userRole = userRole;
        this.accessToken = accessToken;
    }

}