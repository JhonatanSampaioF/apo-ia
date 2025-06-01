package fiap.kciao.apo_ia.usecases.domains.implementations;

import fiap.kciao.apo_ia.domains.Local;
import fiap.kciao.apo_ia.gateways.dtos.requests.domains.locais.LocalCreateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.requests.domains.locais.LocalUpdateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.responses.domains.locais.LocalFullResponseDto;
import fiap.kciao.apo_ia.gateways.mappers.domains.LocalMapper;
import fiap.kciao.apo_ia.usecases.domains.interfaces.CrudLocal;
import fiap.kciao.apo_ia.usecases.services.query.LocalQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static fiap.kciao.apo_ia.gateways.mappers.domains.LocalMapper.*;

@Service
@RequiredArgsConstructor
public class CrudLocalImpl implements CrudLocal {
    private final LocalQueryService localQueryService;

    @Override
    public LocalFullResponseDto create(LocalCreateRequestDto localCreateRequestDto) {
        return toFullResponseDto(localQueryService.save(toEntityCreate(localCreateRequestDto)));
    }

    @Override
    public LocalFullResponseDto update(String id, LocalUpdateRequestDto localUpdateRequestDto) {
        Local local = localQueryService.findByIdOrThrow(id);

        local.setNome(localUpdateRequestDto.getNome() != null
                ? localUpdateRequestDto.getNome() : local.getNome());
        local.setEndereco(localUpdateRequestDto.getEndereco() != null
                ? localUpdateRequestDto.getEndereco() : local.getEndereco());
        local.setCapacidade(localUpdateRequestDto.getCapacidade() != null
                ? localUpdateRequestDto.getCapacidade() : local.getCapacidade());
        local.setQtd_abrigados(localUpdateRequestDto.getQtd_abrigados() != null
                ? localUpdateRequestDto.getQtd_abrigados() : local.getQtd_abrigados());

        return toFullResponseDto(localQueryService.save(local));
    }

    @Override
    public List<LocalFullResponseDto> findAll() {
        return toFullResponseDto(localQueryService.findAll());
    }

    @Override
    public LocalFullResponseDto findById(String id) {
        return toFullResponseDto(localQueryService.findByIdOrThrow(id));
    }

    @Override
    public void delete(String id) {
        localQueryService.deleteById(id);
    }
}
