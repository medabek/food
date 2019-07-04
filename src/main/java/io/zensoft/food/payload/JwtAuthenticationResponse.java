package io.zensoft.food.payload;

import io.zensoft.food.model.RoleName;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class JwtAuthenticationResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private Set<RoleName> userRole;

    public JwtAuthenticationResponse(String accessToken, Set<RoleName> userRole) {
        this.userRole = userRole;
        this.accessToken = accessToken;
    }

}
