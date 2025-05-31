package fiap.kciao.apo_ia.gateways.dtos.requests.services;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String email;
    private String senha;
}

