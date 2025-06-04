package fiap.kciao.apo_ia.gateways.repositories.mongo;

import fiap.kciao.apo_ia.gateways.dtos.reports.RelatorioOcupacaoLocal;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RelatorioTotaisLocalRepository extends MongoRepository<RelatorioOcupacaoLocal, String> {
}
