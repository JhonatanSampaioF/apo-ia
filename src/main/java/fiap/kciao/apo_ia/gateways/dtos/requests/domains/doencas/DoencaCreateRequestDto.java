package fiap.kciao.apo_ia.gateways.dtos.requests.domains.doencas;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoencaCreateRequestDto {
    @NotEmpty
    private String nome;
    @NotEmpty
    private String gravidade;
}
