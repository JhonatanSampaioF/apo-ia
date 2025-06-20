package fiap.kciao.apo_ia.usecases.services.query;

import fiap.kciao.apo_ia.domains.Habilidade;
import fiap.kciao.apo_ia.gateways.dtos.responses.domains.habilidades.HabilidadeFullResponseDto;
import fiap.kciao.apo_ia.gateways.repositories.HabilidadeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HabilidadeQueryService {

    private final HabilidadeRepository habilidadeRepository;

    public Habilidade save(Habilidade habilidade) {
        return habilidadeRepository.save(habilidade);
    }

    public Habilidade findByIdOrThrow(String id) {
        return habilidadeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Habilidade não encontrada"));
    }

    public List<Habilidade> findAll() {
        return habilidadeRepository.findAll();
    }

    public void deleteById(String id) {
        habilidadeRepository.deleteById(id);
    }

    public Habilidade findByIdOrElseNull(String id) {
        return habilidadeRepository.findById(id)
                .orElse(null);
    }

    public List<Habilidade> findAllById(List<String> ids) {
        return habilidadeRepository.findAllById(ids);
    }

    public List<Habilidade> findAllByGroupId(String id) {
        return habilidadeRepository.findAllByGrupoHabilidadeId(id);
    }
}
