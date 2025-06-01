package fiap.kciao.apo_ia.usecases.domains.implementations;

import fiap.kciao.apo_ia.domains.Abrigado;
import fiap.kciao.apo_ia.domains.Doenca;
import fiap.kciao.apo_ia.domains.Local;
import fiap.kciao.apo_ia.gateways.dtos.requests.domains.abrigados.AbrigadoCreateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.requests.domains.abrigados.AbrigadoUpdateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.requests.domains.voluntarios.VoluntarioCreateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.responses.domains.abrigados.AbrigadoFullResponseDto;
import fiap.kciao.apo_ia.gateways.dtos.responses.domains.voluntarios.VoluntarioFullResponseDto;
import fiap.kciao.apo_ia.usecases.domains.interfaces.CrudVoluntario;
import fiap.kciao.apo_ia.usecases.enums.ManageAction;
import fiap.kciao.apo_ia.usecases.domains.interfaces.CrudAbrigado;
import fiap.kciao.apo_ia.usecases.services.query.AbrigadoQueryService;
import fiap.kciao.apo_ia.usecases.services.query.DoencaQueryService;
import fiap.kciao.apo_ia.usecases.services.query.LocalQueryService;
import fiap.kciao.apo_ia.usecases.services.query.VoluntarioQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static fiap.kciao.apo_ia.gateways.mappers.domains.AbrigadoMapper.*;

@Service
@RequiredArgsConstructor
public class CrudAbrigadoImpl implements CrudAbrigado {
    private final AbrigadoQueryService abrigadoQueryService;
    private final LocalQueryService localQueryService;
    private final DoencaQueryService doencaQueryService;
    private final CrudVoluntario crudVoluntario;
    private final VoluntarioQueryService voluntarioQueryService;

    @Override
    public AbrigadoFullResponseDto create(AbrigadoCreateRequestDto abrigadoCreateRequestDto) {
        Abrigado abrigado = abrigadoQueryService.save(toEntityCreate(abrigadoCreateRequestDto));

        Local local = localQueryService.findByIdOrThrow(abrigadoCreateRequestDto.getLocalId());
        if (abrigadoCreateRequestDto.getDoencaIds() != null && !abrigadoCreateRequestDto.getDoencaIds().isEmpty()) {
            abrigado.setDoencaIds(new ArrayList<>());
            List<Doenca> doencas = doencaQueryService.findAllById(abrigadoCreateRequestDto.getDoencaIds());
            if (doencas != null && !doencas.isEmpty()) {
                abrigado.getDoencaIds().addAll(doencas.stream().map(Doenca::getId).toList());
            }
        }

        abrigado.setLocalId(local.getId());

        Abrigado savedAbrigado = abrigadoQueryService.save(abrigado);

        if (abrigado.getVoluntario()) {
            crudVoluntario.create(VoluntarioCreateRequestDto.builder()
                            .abrigadoId(abrigado.getId())
                            .habilidadeIds(abrigadoCreateRequestDto.getHabilidadeIds())
                            .capacidade_motora(abrigadoCreateRequestDto.getCapacidade_motora())
                    .build());
        }

        return toFullResponseDto(savedAbrigado);
    }

    @Override
    public AbrigadoFullResponseDto update(String id, AbrigadoUpdateRequestDto abrigadoUpdateRequestDto) {
        Abrigado abrigado = abrigadoQueryService.findByIdOrThrow(id);

        abrigado.setNome(abrigadoUpdateRequestDto.getNome() != null
                ? abrigadoUpdateRequestDto.getNome() : abrigado.getNome());
        abrigado.setIdade(abrigadoUpdateRequestDto.getIdade() != null
                ? abrigadoUpdateRequestDto.getIdade() : abrigado.getIdade());
        abrigado.setAltura(abrigadoUpdateRequestDto.getAltura() != null
                ? abrigadoUpdateRequestDto.getAltura() : abrigado.getAltura());
        abrigado.setPeso(abrigadoUpdateRequestDto.getPeso() != null
                ? abrigadoUpdateRequestDto.getPeso() : abrigado.getPeso());
        abrigado.setCpf(abrigadoUpdateRequestDto.getCpf() != null
                ? abrigadoUpdateRequestDto.getCpf() : abrigado.getCpf());
        abrigado.setFerimento(abrigadoUpdateRequestDto.getFerimento() != null
                ? abrigadoUpdateRequestDto.getFerimento() : abrigado.getFerimento());

        if (abrigadoUpdateRequestDto.getDoencaIds() != null && !abrigadoUpdateRequestDto.getDoencaIds().isEmpty()) {
            abrigado.setDoencaIds(new ArrayList<>());
            List<Doenca> doencas = doencaQueryService.findAllById(abrigadoUpdateRequestDto.getDoencaIds());
            if (doencas != null && !doencas.isEmpty()) {
                abrigado.setDoencaIds(doencas.stream().map(Doenca::getId).toList());
            }
        }

        if (abrigadoUpdateRequestDto.getVoluntario() && !abrigado.getVoluntario()) {
            abrigado.setVoluntario(true);
            crudVoluntario.create(VoluntarioCreateRequestDto.builder()
                    .abrigadoId(abrigado.getId())
                    .build());
        }

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
        Abrigado abrigado = abrigadoQueryService.findByIdOrThrow(id);
        VoluntarioFullResponseDto voluntario = crudVoluntario.findByAbrigadoId(abrigado.getId());
        voluntarioQueryService.deleteById(voluntario.getId());
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
