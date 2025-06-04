package fiap.kciao.apo_ia.usecases.services.query;

import fiap.kciao.apo_ia.domains.Voluntario;
import fiap.kciao.apo_ia.gateways.repositories.oracle.VoluntarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VoluntarioQueryService {

    private final VoluntarioRepository voluntarioRepository;

    public Voluntario save(Voluntario voluntario) {
        return voluntarioRepository.save(voluntario);
    }

    public Voluntario findByIdOrThrow(String id) {
        return voluntarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Voluntário não encontrado"));
    }

    public List<Voluntario> findAll() {
        return voluntarioRepository.findAll();
    }

    public void deleteById(String id) {
        voluntarioRepository.deleteById(id);
    }

    public Voluntario findByAbrigadoIdOrElseNull(String id) {
        return voluntarioRepository.findByAbrigado_Id(id)
                .orElse(null);
    }

    public List<Voluntario> findAllByHabilidadeId(String id) {
        return voluntarioRepository.findAllByHabilidades_Id(id);
    }

    public void saveAll(List<Voluntario> voluntarios) {
        voluntarioRepository.saveAll(voluntarios);
    }
}
