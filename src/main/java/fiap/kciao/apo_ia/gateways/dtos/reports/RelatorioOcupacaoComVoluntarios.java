package fiap.kciao.apo_ia.gateways.dtos.reports;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@Document(collection = "relatorio_ocupacao_voluntarios")
public class RelatorioOcupacaoComVoluntarios {
    @Id
    private String id;
    private String nomeLocal;
    private Long totalAbrigados;
    private Long totalVoluntarios;
    private Integer capacidade;
    private Double ocupacaoPercentual;
    private LocalDateTime dataGeracao;
}