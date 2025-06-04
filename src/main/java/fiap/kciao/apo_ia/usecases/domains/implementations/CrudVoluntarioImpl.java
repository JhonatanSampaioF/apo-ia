package fiap.kciao.apo_ia.usecases.domains.implementations;

import fiap.kciao.apo_ia.domains.Abrigado;
import fiap.kciao.apo_ia.domains.Habilidade;
import fiap.kciao.apo_ia.domains.Voluntario;
import fiap.kciao.apo_ia.gateways.dtos.requests.domains.voluntarios.VoluntarioCreateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.requests.domains.voluntarios.VoluntarioUpdateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.responses.domains.voluntarios.VoluntarioFullResponseDto;
import fiap.kciao.apo_ia.usecases.enums.ManageAction;
import fiap.kciao.apo_ia.usecases.domains.interfaces.CrudVoluntario;
import fiap.kciao.apo_ia.usecases.services.query.AbrigadoQueryService;
import fiap.kciao.apo_ia.usecases.services.query.HabilidadeQueryService;
import fiap.kciao.apo_ia.usecases.services.query.VoluntarioQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static fiap.kciao.apo_ia.gateways.mappers.domains.VoluntarioMapper.*;

@Service
@RequiredArgsConstructor
public class CrudVoluntarioImpl implements CrudVoluntario {
    private final VoluntarioQueryService voluntarioQueryService;
    private final AbrigadoQueryService abrigadoQueryService;
    private final HabilidadeQueryService habilidadeQueryService;

    @Override
    public VoluntarioFullResponseDto create(VoluntarioCreateRequestDto voluntarioCreateRequestDto) {
        Voluntario voluntario = toEntityCreate(voluntarioCreateRequestDto);
        Abrigado abrigado = abrigadoQueryService.findByIdOrThrow(voluntarioCreateRequestDto.getAbrigadoId());

        if (voluntarioCreateRequestDto.getHabilidadeIds() != null && !voluntarioCreateRequestDto.getHabilidadeIds().isEmpty()) {
            List<Habilidade> habilidades = habilidadeQueryService.findAllById(voluntarioCreateRequestDto.getHabilidadeIds());
            if (habilidades != null && !habilidades.isEmpty()) {
                voluntario.getHabilidades().addAll(habilidades);
            }
        }

        voluntario.setAbrigado(abrigado);

        return toFullResponseDto(voluntarioQueryService.save(voluntario));
    }

    @Override
    public VoluntarioFullResponseDto update(String id, VoluntarioUpdateRequestDto voluntarioUpdateRequestDto) {
        Voluntario voluntario = voluntarioQueryService.findByIdOrThrow(id);

        voluntario.setCapacidade_motora(voluntarioUpdateRequestDto.getCapacidade_motora() != null
                ? voluntarioUpdateRequestDto.getCapacidade_motora() : voluntario.getCapacidade_motora());

        if (voluntarioUpdateRequestDto.getHabilidadeIds() != null && !voluntarioUpdateRequestDto.getHabilidadeIds().isEmpty()) {
            List<Habilidade> habilidadesAntigas = voluntario.getHabilidades();
            List<Habilidade> habilidades = habilidadeQueryService.findAllById(voluntarioUpdateRequestDto.getHabilidadeIds());
            if (habilidades != null && !habilidades.isEmpty()) {
                for (Habilidade habilidade : habilidades) {
                    if (!habilidadesAntigas.contains(habilidade)) {
                        voluntario.getHabilidades().add(habilidade);
                    }
                }
            }
        }

        return toFullResponseDto(voluntarioQueryService.save(voluntario));
    }

    @Override
    public VoluntarioFullResponseDto findById(String id) {
        return toFullResponseDto(voluntarioQueryService.findByIdOrThrow(id));
    }

    @Override
    public List<VoluntarioFullResponseDto> findAll() {
        return toFullResponseDto(voluntarioQueryService.findAll());
    }

    @Override
    public void delete(String id) {
        Voluntario voluntario = voluntarioQueryService.findByIdOrThrow(id);
        Abrigado abrigado = abrigadoQueryService.findByIdOrThrow(voluntario.getAbrigado().getId());
        abrigado.setVoluntario(false);
        abrigadoQueryService.save(abrigado);
        voluntarioQueryService.deleteById(id);
    }

    @Override
    public VoluntarioFullResponseDto manageHabilidade(String voluntarioId, String habilidadeId, ManageAction action) {
        Voluntario voluntario = voluntarioQueryService.findByIdOrThrow(voluntarioId);
        Habilidade habilidade = habilidadeQueryService.findByIdOrThrow(habilidadeId);

        switch (action) {
            case ADD -> voluntario.getHabilidades().add(habilidade);
            case REMOVE -> voluntario.getHabilidades().remove(habilidade);
        }

        return toFullResponseDto(voluntarioQueryService.save(voluntario));
    }

    @Override
    public VoluntarioFullResponseDto manageAlocacao(String voluntarioId, String alocacao, ManageAction action) {
        Voluntario voluntario = voluntarioQueryService.findByIdOrThrow(voluntarioId);

        switch (action) {
            case ADD -> voluntario.setAlocacao(alocacao);
            case REMOVE -> voluntario.setAlocacao(null);
        }

        return toFullResponseDto(voluntarioQueryService.save(voluntario));
    }

    @Override
    public VoluntarioFullResponseDto findByAbrigadoId(String id) {
        return toFullResponseDto(voluntarioQueryService.findByAbrigadoIdOrElseNull(id));
    }
}
