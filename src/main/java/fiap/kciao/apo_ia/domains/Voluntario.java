package fiap.kciao.apo_ia.domains;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "voluntarios")
public class Voluntario {
    @Id
    private String id;
    private String alocacao;
    private String capacidade_motora;

    private String abrigadoId;
    private List<String> habilidadeIds;
}
