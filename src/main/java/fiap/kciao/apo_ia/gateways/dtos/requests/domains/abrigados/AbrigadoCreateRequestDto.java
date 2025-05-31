package fiap.kciao.apo_ia.gateways.dtos.requests.domains.abrigados;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

@Data
@Builder
public class AbrigadoCreateRequestDto {
    @NotEmpty
    private String nome;
    @NotNull
    private Integer idade;
    @NotNull
    private Double altura;
    @NotNull
    private Double peso;
    @NotEmpty
    @CPF
    private String cpf;
    @NotNull
    private Boolean voluntario;
    @NotEmpty
    private String ferimento;
    @NotNull
    private String localId;
}
