package fiap.kciao.apo_ia.usecases.domains.implementations;

import fiap.kciao.apo_ia.domains.Doenca;
import fiap.kciao.apo_ia.gateways.dtos.requests.domains.doencas.DoencaCreateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.requests.domains.doencas.DoencaUpdateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.responses.domains.doencas.DoencaFullResponseDto;
import fiap.kciao.apo_ia.gateways.mappers.domains.DoencaMapper;
import fiap.kciao.apo_ia.usecases.domains.interfaces.CrudDoenca;
import fiap.kciao.apo_ia.usecases.services.query.DoencaQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static fiap.kciao.apo_ia.gateways.mappers.domains.DoencaMapper.*;

@Service
@RequiredArgsConstructor
public class CrudDoencaImpl implements CrudDoenca {
    private final DoencaQueryService doencaQueryService;

    @Override
    public DoencaFullResponseDto create(DoencaCreateRequestDto doencaCreateRequestDto) {
        return toFullResponseDto(doencaQueryService.save(toEntityCreate(doencaCreateRequestDto)));
    }

    @Override
    public DoencaFullResponseDto update(String id, DoencaUpdateRequestDto doencaUpdateRequestDto) {
        Doenca doenca = doencaQueryService.findByIdOrThrow(id);

        doenca.setNome(doencaUpdateRequestDto.getNome());
        doenca.setGravidade(doencaUpdateRequestDto.getGravidade());

        return toFullResponseDto(doencaQueryService.save(doenca));
    }

    @Override
    public DoencaFullResponseDto findById(String id) {
        return toFullResponseDto(doencaQueryService.findByIdOrThrow(id));
    }

    @Override
    public List<DoencaFullResponseDto> findAll() {
        return toFullResponseDto(doencaQueryService.findAll());
    }

    @Override
    public void delete(String id) {
        doencaQueryService.deleteById(id);
    }
}
