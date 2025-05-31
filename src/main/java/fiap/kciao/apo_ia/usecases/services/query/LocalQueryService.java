package fiap.kciao.apo_ia.usecases.services.query;

import fiap.kciao.apo_ia.domains.Local;
import fiap.kciao.apo_ia.gateways.repositories.LocalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocalQueryService {

    private final LocalRepository localRepository;

    public Local save(Local local) {
        return localRepository.save(local);
    }

    public Local findByIdOrThrow(String id) {
        return localRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Local não encontrado"));
    }

    public List<Local> findAll() {
        return localRepository.findAll();
    }

    public void deleteById(String id) {
        localRepository.deleteById(id);
    }
}
