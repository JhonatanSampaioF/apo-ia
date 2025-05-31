package fiap.kciao.apo_ia.gateways.dtos.responses.domains.usuarios;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UsuarioFullResponseDto {
    private String id;
    private String nome;
    private String email;
}
