package fiap.kciao.apo_ia.gateways.repositories.oracle;

import fiap.kciao.apo_ia.domains.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    Optional<Usuario> findByEmail(String email);
}
