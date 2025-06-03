package fiap.kciao.apo_ia.gateways.dtos.requests.domains.locais;

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
public class LocalCreateRequestDto {
    @NotEmpty(message = "{local.nome.required}")
    private String nome;
    @NotEmpty(message = "{local.endereco.required}")
    private String endereco;
    @NotNull(message = "{local.capacidade.required}")
    private Integer capacidade;
    @NotNull(message = "{local.qtd_abrigados.required}")
    private Integer qtd_abrigados;
}
