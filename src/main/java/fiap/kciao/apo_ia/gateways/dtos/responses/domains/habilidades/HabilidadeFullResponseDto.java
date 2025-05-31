package fiap.kciao.apo_ia.gateways.dtos.responses.domains.habilidades;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class HabilidadeFullResponseDto {
    private String id;
    private String nome;
    private Integer prioridade;
    private String grupoHabilidadeId;
}
