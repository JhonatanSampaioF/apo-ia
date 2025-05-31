package fiap.kciao.apo_ia.gateways.repositories;

import fiap.kciao.apo_ia.domains.Abrigado;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AbrigadoRepository extends MongoRepository<Abrigado, String> {
}
