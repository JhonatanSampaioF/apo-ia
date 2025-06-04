package fiap.kciao.apo_ia.gateways.repositories.oracle;

import fiap.kciao.apo_ia.domains.Abrigado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AbrigadoRepository extends JpaRepository<Abrigado, String> {
    List<Abrigado> findAllByLocal_Id(String localId);
    List<Abrigado> findAllByDoencas_Id(String id);
}
