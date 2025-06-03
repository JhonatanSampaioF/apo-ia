package fiap.kciao.apo_ia.gateways.dtos.requests.services;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginRequestDto {
    @NotEmpty(message = "{login.email.required}")
    private String email;
    @NotEmpty(message = "{login.senha.required}")
    private String senha;
}

