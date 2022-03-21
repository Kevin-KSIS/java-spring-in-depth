package lowk.learning.moneyMana.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class AuthenRequestDTO {
    private long id;

    @NotNull(message = "Username not null")
    @NotBlank(message = "Username not blank")
    private String username;

    @NotNull(message = "displayName not null")
    @NotBlank(message = "displayName not blank")
    private String displayName;

    @NotNull(message = "email not null")
    @NotBlank(message = "email not blank")
    @Email(regexp = ".+@.+\\.[a-z]{2,5}", flags = Pattern.Flag.CASE_INSENSITIVE)
    private String email;

    @NotNull(message = "password not null")
    @NotBlank(message = "password not blank")
    private String password;

}
