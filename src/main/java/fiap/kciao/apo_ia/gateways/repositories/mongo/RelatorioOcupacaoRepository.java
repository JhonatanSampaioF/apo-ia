package fiap.kciao.apo_ia.gateways.repositories.mongo;

import fiap.kciao.apo_ia.gateways.dtos.reports.RelatorioOcupacaoComVoluntarios;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RelatorioOcupacaoRepository extends MongoRepository<RelatorioOcupacaoComVoluntarios, String> {
}
