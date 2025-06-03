package fiap.kciao.apo_ia.domains;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class Voluntario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String alocacao;
    private String capacidade_motora;

    @OneToOne
    private Abrigado abrigado;

    @ManyToMany
    @JsonManagedReference
    private List<Habilidade> habilidades;
}
