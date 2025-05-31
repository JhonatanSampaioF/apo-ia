package fiap.kciao.apo_ia.gateways.repositories;

import fiap.kciao.apo_ia.domains.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    Optional<Usuario> findByEmail(String email);
}
