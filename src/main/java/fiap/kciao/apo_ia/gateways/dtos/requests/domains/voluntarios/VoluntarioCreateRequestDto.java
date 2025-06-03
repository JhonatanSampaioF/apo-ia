package fiap.kciao.apo_ia.gateways.dtos.requests.domains.voluntarios;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VoluntarioCreateRequestDto {
    @NotEmpty(message = "{voluntario.capacidade_motora.required}")
    private String capacidade_motora;
    @NotNull(message = "{voluntario.abrigadoId.required}")
    private String abrigadoId;
    private List<String> habilidadeIds;
}
