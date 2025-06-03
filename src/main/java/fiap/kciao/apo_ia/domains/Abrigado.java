package fiap.kciao.apo_ia.domains;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Abrigado {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String nome;
    private Integer idade;
    private Double altura;
    private Double peso;
    private String cpf;
    private Boolean voluntario;
    private String ferimento;

    @ManyToOne
    private Local local;

    @ManyToMany
    @JsonManagedReference
    private List<Doenca> doencas;
}
