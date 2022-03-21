package lowk.learning.moneyMana.dto;

import lombok.Data;

@Data
public class AuthenResponseDTO {

    private long id;

    private String username;
    private String displayName;
    private String email;
}
