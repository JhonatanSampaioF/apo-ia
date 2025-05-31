package fiap.kciao.apo_ia.gateways.dtos.requests.domains.doencas;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DoencaCreateRequestDto {
    @NotEmpty
    private String nome;
    @NotEmpty
    private String gravidade;
}
