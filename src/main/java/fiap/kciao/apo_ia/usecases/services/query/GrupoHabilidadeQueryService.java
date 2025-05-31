package fiap.kciao.apo_ia.usecases.services.query;

import fiap.kciao.apo_ia.domains.GrupoHabilidade;
import fiap.kciao.apo_ia.gateways.repositories.GrupoHabilidadeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GrupoHabilidadeQueryService {
    private final GrupoHabilidadeRepository grupoHabilidadeRepository;

    public GrupoHabilidade save(GrupoHabilidade grupoHabilidade) {
        return grupoHabilidadeRepository.save(grupoHabilidade);
    }

    public GrupoHabilidade findByIdOrThrow(String id) {
        return grupoHabilidadeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Grupo Habilidade não encontrada"));
    }

    public List<GrupoHabilidade> findAll() {
        return grupoHabilidadeRepository.findAll();
    }

    public void deleteById(String id) {
        grupoHabilidadeRepository.deleteById(id);
    }

    public GrupoHabilidade findByIdOrElseNull(String id) {
        return grupoHabilidadeRepository.findById(id)
                .orElse(null);
    }
}
