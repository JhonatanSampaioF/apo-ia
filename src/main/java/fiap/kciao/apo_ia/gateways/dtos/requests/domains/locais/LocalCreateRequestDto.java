package fiap.kciao.apo_ia.gateways.dtos.requests.domains.locais;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LocalCreateRequestDto {
    @NotEmpty
    private String nome;
    @NotEmpty
    private String endereco;
    @NotNull
    private Integer capacidade;
    @NotNull
    private Integer qtd_abrigados;
}
