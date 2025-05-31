package fiap.kciao.apo_ia.gateways.repositories;

import fiap.kciao.apo_ia.domains.Voluntario;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VoluntarioRepository extends MongoRepository<Voluntario, String> {
}
