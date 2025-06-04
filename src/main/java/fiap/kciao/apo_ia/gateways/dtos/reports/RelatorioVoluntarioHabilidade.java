package fiap.kciao.apo_ia.gateways.dtos.reports;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@Document(collection = "relatorio_voluntario_habilidade")
public class RelatorioVoluntarioHabilidade {
    @Id
    private String id;
    private String nomeVoluntario;
    private String habilidade;
    private String grupoHabilidade;
    private String nomeLocal;
    private LocalDateTime dataGeracao;
}
