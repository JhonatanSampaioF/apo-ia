package fiap.kciao.apo_ia.gateways.repositories.mongo;

import fiap.kciao.apo_ia.gateways.dtos.reports.RelatorioVoluntarioHabilidade;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RelatorioVoluntarioHabilidadeRepository extends MongoRepository<RelatorioVoluntarioHabilidade, String> {
}
