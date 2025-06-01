package fiap.kciao.apo_ia.gateways.dtos.requests.domains.usuarios;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioCreateRequestDto {
    @NotEmpty
    private String nome;
    @NotEmpty
    private String email;
    @NotEmpty
    private String senha;
}
