package fiap.kciao.apo_ia.gateways.repositories;

import fiap.kciao.apo_ia.domains.Local;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LocalRepository extends MongoRepository<Local, String> {
}
