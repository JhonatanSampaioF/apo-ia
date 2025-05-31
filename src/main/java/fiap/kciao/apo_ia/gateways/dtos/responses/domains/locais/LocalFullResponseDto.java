package fiap.kciao.apo_ia.gateways.dtos.responses.domains.locais;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class LocalFullResponseDto {
    private String id;
    private String nome;
    private String endereco;
    private Integer capacidade;
    private Integer qtd_abrigados;
}
