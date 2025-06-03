package fiap.kciao.apo_ia.gateways.dtos.requests.domains.habilidades;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HabilidadeCreateRequestDto {
    @NotEmpty(message = "{habilidade.nome.required}")
    private String nome;
    @NotNull(message = "{habilidade.prioridade.required}")
    private Integer prioridade;
    @NotEmpty(message = "{habilidade.grupohabilidadeId.required}")
    private String grupoHabilidadeId;
}
