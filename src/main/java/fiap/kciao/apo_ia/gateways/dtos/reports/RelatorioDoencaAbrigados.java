package fiap.kciao.apo_ia.gateways.dtos.reports;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@Document(collection = "relatorio_doenca_abrigados")
public class RelatorioDoencaAbrigados {
    @Id
    private String id;
    private String nomeDoenca;
    private Long totalAbrigados;
    private LocalDateTime dataGeracao;
}

