package ua.com.lineup.realestate.model.request;

import lombok.Getter;
import lombok.Setter;
import ua.com.lineup.realestate.model.enumeration.RoleName;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SignUpRequest {

    private Long id;

    @NotBlank
    @Size(min = 4, max = 40)
    private String name;

    @NotBlank
    @Size(max = 40)
    @Email
    private String email;

    @NotBlank
    @Size(min = 6, max = 20)
    private String password;

    private RoleName role;
}
