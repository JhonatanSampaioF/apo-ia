package fiap.kciao.apo_ia.gateways.dtos.requests.domains.gruposHabilidades;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GrupoHabilidadeUpdateRequestDto {
    private String nome;
}
