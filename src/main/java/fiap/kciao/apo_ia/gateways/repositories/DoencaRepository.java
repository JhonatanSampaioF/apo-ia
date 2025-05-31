package fiap.kciao.apo_ia.gateways.repositories;

import fiap.kciao.apo_ia.domains.Doenca;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DoencaRepository extends MongoRepository<Doenca, String> {
}
