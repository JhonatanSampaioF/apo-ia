package fiap.kciao.apo_ia.gateways.repositories;

import fiap.kciao.apo_ia.domains.Voluntario;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface VoluntarioRepository extends MongoRepository<Voluntario, String> {
    Optional<Voluntario> findByAbrigadoId(String id);

    List<Voluntario> findAllByHabilidadeIdsContaining(String id);
}
