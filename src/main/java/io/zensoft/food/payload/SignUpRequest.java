package io.zensoft.food.payload;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
public class SignUpRequest {
    @NotBlank
    @Size(min = 3, max = 25)
    private String firstname;

    @NotBlank
    @Size(min = 3, max = 25)
    private String lastname;

    @NotBlank
    @Size(max = 40)
    @Email
    private String email;

    @NotBlank
    @Size(min = 6, max = 20)
    private String password;

}
