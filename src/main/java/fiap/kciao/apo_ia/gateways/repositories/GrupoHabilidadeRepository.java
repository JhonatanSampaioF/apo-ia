package fiap.kciao.apo_ia.gateways.repositories;

import fiap.kciao.apo_ia.domains.GrupoHabilidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GrupoHabilidadeRepository extends JpaRepository<GrupoHabilidade, String> {
}
