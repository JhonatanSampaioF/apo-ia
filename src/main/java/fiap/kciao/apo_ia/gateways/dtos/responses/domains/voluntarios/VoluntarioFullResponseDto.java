package fiap.kciao.apo_ia.gateways.dtos.responses.domains.voluntarios;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class VoluntarioFullResponseDto {
    private String id;
    private String alocacao;
    private String capacidade_motora;
    private String abrigadoId;
    private String abrigadoNome;
    private String abrigadoCpf;
    private List<String> habilidadeIds;
}
