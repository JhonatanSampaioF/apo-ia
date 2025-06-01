package fiap.kciao.apo_ia.gateways.repositories;

import fiap.kciao.apo_ia.domains.Habilidade;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface HabilidadeRepository extends MongoRepository<Habilidade, String> {
    List<Habilidade> findAllByGrupoHabilidadeId(String id);
}
