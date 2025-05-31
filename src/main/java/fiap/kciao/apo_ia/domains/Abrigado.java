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
@Document(collection = "abrigados")
public class Abrigado {
    @Id
    private String id;
    private String nome;
    private Integer idade;
    private Double altura;
    private Double peso;
    private String cpf;
    private Boolean voluntario;
    private String ferimento;

    private String localId;
    private List<String> doencaIds;
}
