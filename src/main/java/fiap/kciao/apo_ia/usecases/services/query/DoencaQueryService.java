package fiap.kciao.apo_ia.usecases.services.query;

import fiap.kciao.apo_ia.domains.Doenca;
import fiap.kciao.apo_ia.gateways.repositories.DoencaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoencaQueryService {

    private final DoencaRepository doencaRepository;

    public Doenca save(Doenca doenca) {
        return doencaRepository.save(doenca);
    }

    public Doenca findByIdOrThrow(String id) {
        return doencaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Doença não encontrada"));
    }

    public List<Doenca> findAll() {
        return doencaRepository.findAll();
    }

    public void deleteById(String id) {
        doencaRepository.deleteById(id);
    }
}
