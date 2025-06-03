package fiap.kciao.apo_ia.gateways.repositories;

import fiap.kciao.apo_ia.domains.Habilidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HabilidadeRepository extends JpaRepository<Habilidade, String> {
    List<Habilidade> findAllByGrupoHabilidade_Id(String id);
}
