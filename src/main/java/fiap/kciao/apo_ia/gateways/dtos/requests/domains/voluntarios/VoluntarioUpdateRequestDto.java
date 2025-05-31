package fiap.kciao.apo_ia.gateways.dtos.requests.domains.voluntarios;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VoluntarioUpdateRequestDto {
    private String capacidade_motora;
}
