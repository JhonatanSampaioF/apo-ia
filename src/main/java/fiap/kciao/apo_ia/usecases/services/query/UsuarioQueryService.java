package fiap.kciao.apo_ia.usecases.services.query;

import fiap.kciao.apo_ia.domains.Usuario;
import fiap.kciao.apo_ia.gateways.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioQueryService {

    private final UsuarioRepository usuarioRepository;

    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario findByIdOrThrow(String id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
    }

    public Usuario findByEmailOrThrow(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
    }

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public void deleteById(String id) {
        usuarioRepository.deleteById(id);
    }

    public Usuario findByEmailOrNull(String email) {
        return usuarioRepository.findByEmail(email)
                .orElse(null);
    }
}
