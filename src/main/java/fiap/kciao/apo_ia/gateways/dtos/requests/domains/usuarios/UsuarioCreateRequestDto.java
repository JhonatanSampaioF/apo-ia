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
    @NotEmpty(message = "{usuario.nome.required}")
    private String nome;
    @NotEmpty(message = "{usuario.email.required}")
    private String email;
    @NotEmpty(message = "{usuario.senha.required}")
    private String senha;
}
