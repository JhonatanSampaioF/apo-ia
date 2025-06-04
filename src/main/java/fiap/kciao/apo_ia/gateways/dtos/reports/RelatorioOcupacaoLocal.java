package fiap.kciao.apo_ia.gateways.dtos.reports;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@Document(collection = "relatorio_ocupacao")
public class RelatorioOcupacaoLocal {
    @Id
    private String id;
    private String nomeLocal;
    private Integer capacidade;
    private Integer qtdAbrigados;
    private Double percentualOcupacao;
    private LocalDateTime dataGeracao;
}