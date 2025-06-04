package fiap.kciao.apo_ia.gateways.repositories.mongo;

import fiap.kciao.apo_ia.gateways.dtos.reports.RelatorioDoencaAbrigados;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RelatorioDoencaAbrigadosRepository extends MongoRepository<RelatorioDoencaAbrigados, String> {
}
