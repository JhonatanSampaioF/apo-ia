package fiap.kciao.apo_ia.gateways.dtos.reports;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@Document(collection = "relatorio_idade_abrigados")
public class RelatorioIdadeAbrigadosLocal {
    @Id
    private String id;
    private String nomeLocal;
    private Long totalAbrigados;
    private Double idadeMedia;
    private Integer idadeMaxima;
    private Integer idadeMinima;
    private LocalDateTime dataGeracao;
}

