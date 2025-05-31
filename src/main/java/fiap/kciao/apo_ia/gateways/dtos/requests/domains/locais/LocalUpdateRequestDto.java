package fiap.kciao.apo_ia.gateways.dtos.requests.domains.locais;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LocalUpdateRequestDto {
    private String nome;
    private String endereco;
    private Integer capacidade;
    private Integer qtd_abrigados;
}
