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
    @NotEmpty
    private String nome;
    @NotNull
    private Integer prioridade;
    @NotEmpty
    private String grupoHabilidadeId;
}
