package fiap.kciao.apo_ia.gateways.dtos.requests.domains.gruposHabilidades;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GrupoHabilidadeCreateRequestDto {
    @NotEmpty
    private String nome;
    @NotEmpty
    private String grupoHabilidadeId;
}
