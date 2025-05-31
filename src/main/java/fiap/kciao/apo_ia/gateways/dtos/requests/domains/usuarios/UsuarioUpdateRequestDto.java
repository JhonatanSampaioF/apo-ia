package fiap.kciao.apo_ia.gateways.dtos.requests.domains.usuarios;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioUpdateRequestDto {
    private String name;
    private String email;
    private String senha;
}
