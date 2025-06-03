package fiap.kciao.apo_ia.gateways.dtos.requests.domains.abrigados;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AbrigadoCreateRequestDto {
    @NotEmpty(message = "{abrigado.nome.required}")
    private String nome;
    @NotNull(message = "{abrigado.idade.required}")
    private Integer idade;
    @NotNull(message = "{abrigado.altura.required}")
    private Double altura;
    @NotNull(message = "{abrigado.peso.required}")
    private Double peso;
    @NotEmpty(message = "{abrigado.cpf.required}")
    @CPF
    private String cpf;
    @NotNull(message = "{abrigado.voluntario.required}")
    private Boolean voluntario;
    @NotEmpty(message = "{abrigado.ferimento.required}")
    private String ferimento;
    @NotNull(message = "{abrigado.localId.required}")
    private String localId;
    private List<String> doencaIds;
    private List<String> habilidadeIds;
    private String capacidade_motora;
}
