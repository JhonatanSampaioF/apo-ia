package fiap.kciao.apo_ia.gateways.repositories;

import fiap.kciao.apo_ia.domains.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {
}
