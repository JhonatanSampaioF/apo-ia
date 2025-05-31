package fiap.kciao.apo_ia.gateways.dtos.requests.domains.usuarios;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioCreateRequestDto {
    @NotEmpty
    private String name;
    @NotEmpty
    private String email;
    @NotEmpty
    private String senha;
}
