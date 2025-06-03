package fiap.kciao.apo_ia.domains;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Habilidade {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String nome;
    private Integer prioridade;

    @ManyToOne
    private GrupoHabilidade grupoHabilidade;

    @ManyToMany
    @JsonBackReference
    private List<Voluntario> voluntarios;
}
