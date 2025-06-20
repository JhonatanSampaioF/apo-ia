package fiap.kciao.apo_ia.gateways.dtos.requests.domains.abrigados;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AbrigadoUpdateRequestDto {
    private String nome;
    private Integer idade;
    private Double altura;
    private Double peso;
    private String cpf;
    private Boolean voluntario;
    private String ferimento;
    private List<String> doencaIds;
}
