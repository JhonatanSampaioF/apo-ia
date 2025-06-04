package fiap.kciao.apo_ia.gateways.repositories.oracle;

import fiap.kciao.apo_ia.domains.Voluntario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VoluntarioRepository extends JpaRepository<Voluntario, String> {
    Optional<Voluntario> findByAbrigado_Id(String id);

    List<Voluntario> findAllByHabilidades_Id(String id);
}
