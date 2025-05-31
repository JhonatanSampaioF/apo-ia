package fiap.kciao.apo_ia.usecases.services.query;

import fiap.kciao.apo_ia.domains.Abrigado;
import fiap.kciao.apo_ia.gateways.repositories.AbrigadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AbrigadoQueryService {
    private final AbrigadoRepository abrigadoRepository;

    public Abrigado save(Abrigado abrigado) {
        return abrigadoRepository.save(abrigado);
    }

    public Abrigado findByIdOrThrow(String id) {
        return abrigadoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Abrigado não encontrado"));
    }

    public List<Abrigado> findAll() {
        return abrigadoRepository.findAll();
    }

    public void deleteById(String id) {
        abrigadoRepository.deleteById(id);
    }
}
