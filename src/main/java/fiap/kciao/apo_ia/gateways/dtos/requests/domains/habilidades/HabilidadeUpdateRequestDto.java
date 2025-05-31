package fiap.kciao.apo_ia.gateways.dtos.requests.domains.habilidades;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HabilidadeUpdateRequestDto {
    private String nome;
    private Integer prioridade;
}
