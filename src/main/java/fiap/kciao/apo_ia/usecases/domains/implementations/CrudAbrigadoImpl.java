package fiap.kciao.apo_ia.usecases.domains.implementations;

import fiap.kciao.apo_ia.domains.Abrigado;
import fiap.kciao.apo_ia.domains.Doenca;
import fiap.kciao.apo_ia.domains.Local;
import fiap.kciao.apo_ia.gateways.dtos.requests.domains.abrigados.AbrigadoCreateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.requests.domains.abrigados.AbrigadoUpdateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.responses.domains.abrigados.AbrigadoFullResponseDto;
import fiap.kciao.apo_ia.usecases.enums.ManageAction;
import fiap.kciao.apo_ia.usecases.domains.interfaces.CrudAbrigado;
import fiap.kciao.apo_ia.usecases.services.query.AbrigadoQueryService;
import fiap.kciao.apo_ia.usecases.services.query.DoencaQueryService;
import fiap.kciao.apo_ia.usecases.services.query.LocalQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static fiap.kciao.apo_ia.gateways.mappers.domains.AbrigadoMapper.*;

@Service
@RequiredArgsConstructor
public class CrudAbrigadoImpl implements CrudAbrigado {
    private final AbrigadoQueryService abrigadoQueryService;
    private final LocalQueryService localQueryService;
    private final DoencaQueryService doencaQueryService;

    @Override
    public AbrigadoFullResponseDto create(AbrigadoCreateRequestDto abrigadoCreateRequestDto) {
        Abrigado abrigado = abrigadoQueryService.save(toEntityCreate(abrigadoCreateRequestDto));

        Local local = localQueryService.findByIdOrThrow(abrigadoCreateRequestDto.getLocalId());

        abrigado.setLocalId(local.getId());

        return toFullResponseDto(abrigadoQueryService.save(abrigado));
    }

    @Override
    public AbrigadoFullResponseDto update(String id, AbrigadoUpdateRequestDto abrigadoUpdateRequestDto) {
        Abrigado abrigado = abrigadoQueryService.findByIdOrThrow(id);

        abrigado.setNome(abrigadoUpdateRequestDto.getNome());
        abrigado.setIdade(abrigadoUpdateRequestDto.getIdade());
        abrigado.setAltura(abrigadoUpdateRequestDto.getAltura());
        abrigado.setPeso(abrigadoUpdateRequestDto.getPeso());
        abrigado.setCpf(abrigadoUpdateRequestDto.getCpf());
        abrigado.setVoluntario(abrigadoUpdateRequestDto.getVoluntario());
        abrigado.setFerimento(abrigadoUpdateRequestDto.getFerimento());

        return toFullResponseDto(abrigadoQueryService.save(abrigado));
    }

    @Override
    public AbrigadoFullResponseDto findById(String id) {
        return toFullResponseDto(abrigadoQueryService.findByIdOrThrow(id));
    }

    @Override
    public List<AbrigadoFullResponseDto> findAll() {
        return toFullResponseDto(abrigadoQueryService.findAll());
    }

    @Override
    public void delete(String id) {
        abrigadoQueryService.deleteById(id);
    }

    @Override
    public AbrigadoFullResponseDto manageDoenca(String doencaId, String abrigadoId, ManageAction action) {
        Abrigado abrigado = abrigadoQueryService.findByIdOrThrow(abrigadoId);
        Doenca doenca = doencaQueryService.findByIdOrThrow(doencaId);

        switch (action) {
            case ADD -> abrigado.getDoencaIds().add(doenca.getId());
            case REMOVE -> abrigado.getDoencaIds().remove(doenca.getId());
        }

        return toFullResponseDto(abrigadoQueryService.save(abrigado));
    }
}
