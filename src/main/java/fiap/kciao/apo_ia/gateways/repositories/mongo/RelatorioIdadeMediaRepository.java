package fiap.kciao.apo_ia.gateways.repositories.mongo;

import fiap.kciao.apo_ia.gateways.dtos.reports.RelatorioIdadeAbrigadosLocal;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RelatorioIdadeMediaRepository extends MongoRepository<RelatorioIdadeAbrigadosLocal, String> {
}
