package fiap.kciao.apo_ia.usecases.services.query;

import fiap.kciao.apo_ia.domains.Abrigado;
import fiap.kciao.apo_ia.gateways.repositories.oracle.AbrigadoRepository;
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

    public List<Abrigado> findAllByLocalId(String id) {
        return abrigadoRepository.findAllByLocal_Id(id);
    }

    public void deleteById(String id) {
        abrigadoRepository.deleteById(id);
    }

    public List<Abrigado> findAllContainsDoencaId(String id) {
        return abrigadoRepository.findAllByDoencas_Id(id);
    }

    public void saveAll(List<Abrigado> abrigados) {
        abrigadoRepository.saveAll(abrigados);
    }
}
