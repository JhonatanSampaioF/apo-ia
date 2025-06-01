package fiap.kciao.apo_ia.gateways.dtos.requests.domains.gruposHabilidades;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GrupoHabilidadeCreateRequestDto {
    @NotEmpty
    private String nome;
}
