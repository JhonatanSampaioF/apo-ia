package fiap.kciao.apo_ia.gateways.repositories;

import fiap.kciao.apo_ia.domains.Abrigado;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AbrigadoRepository extends MongoRepository<Abrigado, String> {
    List<Abrigado> findAllByLocalId(String localId);
    List<Abrigado> findAllByDoencaIdsContaining(String id);
}
