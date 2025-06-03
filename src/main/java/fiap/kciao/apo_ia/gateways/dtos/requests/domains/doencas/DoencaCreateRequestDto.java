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
    @NotEmpty(message = "{doenca.nome.required}")
    private String nome;
    @NotEmpty(message = "{doenca.gravidade.required}")
    private String gravidade;
}
