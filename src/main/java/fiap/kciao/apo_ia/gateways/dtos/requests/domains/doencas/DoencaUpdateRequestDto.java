package fiap.kciao.apo_ia.gateways.dtos.requests.domains.doencas;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DoencaUpdateRequestDto {
    private String nome;
    private String gravidade;
}
