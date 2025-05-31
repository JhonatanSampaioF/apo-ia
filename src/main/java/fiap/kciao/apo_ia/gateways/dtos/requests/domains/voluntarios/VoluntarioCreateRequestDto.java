package fiap.kciao.apo_ia.gateways.dtos.requests.domains.voluntarios;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VoluntarioCreateRequestDto {
    @NotEmpty
    private String capacidade_motora;
    @NotNull
    private String abrigadoId;
}
