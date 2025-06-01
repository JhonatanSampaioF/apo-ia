package fiap.kciao.apo_ia.usecases.domains.implementations;

import fiap.kciao.apo_ia.domains.Abrigado;
import fiap.kciao.apo_ia.domains.Doenca;
import fiap.kciao.apo_ia.gateways.dtos.requests.domains.doencas.DoencaCreateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.requests.domains.doencas.DoencaUpdateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.responses.domains.doencas.DoencaFullResponseDto;
import fiap.kciao.apo_ia.usecases.domains.interfaces.CrudAbrigado;
import fiap.kciao.apo_ia.usecases.domains.interfaces.CrudDoenca;
import fiap.kciao.apo_ia.usecases.services.query.AbrigadoQueryService;
import fiap.kciao.apo_ia.usecases.services.query.DoencaQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static fiap.kciao.apo_ia.gateways.mappers.domains.DoencaMapper.*;

@Service
@RequiredArgsConstructor
public class CrudDoencaImpl implements CrudDoenca {
    private final DoencaQueryService doencaQueryService;
    private final CrudAbrigado crudAbrigado;
    private final AbrigadoQueryService abrigadoQueryService;

    @Override
    public DoencaFullResponseDto create(DoencaCreateRequestDto doencaCreateRequestDto) {
        return toFullResponseDto(doencaQueryService.save(toEntityCreate(doencaCreateRequestDto)));
    }

    @Override
    public DoencaFullResponseDto update(String id, DoencaUpdateRequestDto doencaUpdateRequestDto) {
        Doenca doenca = doencaQueryService.findByIdOrThrow(id);

        doenca.setNome(doencaUpdateRequestDto.getNome() != null
                ? doencaUpdateRequestDto.getNome() : doenca.getNome());
        doenca.setGravidade(doencaUpdateRequestDto.getGravidade() != null
                ? doencaUpdateRequestDto.getGravidade() : doenca.getGravidade());

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
        Doenca doenca = doencaQueryService.findByIdOrThrow(id);
        List<Abrigado> abrigados = abrigadoQueryService.findAllContainsDoencaId(doenca.getId());
        if (abrigados != null && !abrigados.isEmpty()) {
            for (Abrigado abrigado : abrigados) {
                abrigado.getDoencaIds().remove(doenca.getId());
            }
            abrigadoQueryService.saveAll(abrigados);
        }
        doencaQueryService.deleteById(id);
    }
}
