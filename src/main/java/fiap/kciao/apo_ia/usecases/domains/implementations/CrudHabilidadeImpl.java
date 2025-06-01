package fiap.kciao.apo_ia.usecases.domains.implementations;

import fiap.kciao.apo_ia.domains.GrupoHabilidade;
import fiap.kciao.apo_ia.domains.Habilidade;
import fiap.kciao.apo_ia.domains.Voluntario;
import fiap.kciao.apo_ia.gateways.dtos.requests.domains.habilidades.HabilidadeCreateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.requests.domains.habilidades.HabilidadeUpdateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.responses.domains.habilidades.HabilidadeFullResponseDto;
import fiap.kciao.apo_ia.gateways.mappers.domains.HabilidadeMapper;
import fiap.kciao.apo_ia.usecases.domains.interfaces.CrudHabilidade;
import fiap.kciao.apo_ia.usecases.services.query.GrupoHabilidadeQueryService;
import fiap.kciao.apo_ia.usecases.services.query.HabilidadeQueryService;
import fiap.kciao.apo_ia.usecases.services.query.VoluntarioQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static fiap.kciao.apo_ia.gateways.mappers.domains.HabilidadeMapper.*;

@Service
@RequiredArgsConstructor
public class CrudHabilidadeImpl implements CrudHabilidade {
    private final GrupoHabilidadeQueryService grupoHabilidadeQueryService;
    private final HabilidadeQueryService habilidadeQueryService;
    private final VoluntarioQueryService voluntarioQueryService;

    @Override
    public HabilidadeFullResponseDto create(HabilidadeCreateRequestDto habilidadeCreateRequestDto) {
        Habilidade habilidade = toEntityCreate(habilidadeCreateRequestDto);

        GrupoHabilidade grupoHabilidade = grupoHabilidadeQueryService.findByIdOrThrow(habilidadeCreateRequestDto.getGrupoHabilidadeId());

        habilidade.setGrupoHabilidadeId(grupoHabilidade.getId());

        return toFullResponseDto(habilidadeQueryService.save(habilidade));
    }

    @Override
    public HabilidadeFullResponseDto update(String id, HabilidadeUpdateRequestDto habilidadeUpdateRequestDto) {
        Habilidade habilidade = habilidadeQueryService.findByIdOrThrow(id);

        habilidade.setNome(habilidadeUpdateRequestDto.getNome() != null
                ? habilidadeUpdateRequestDto.getNome() : habilidade.getNome());
        habilidade.setPrioridade(habilidadeUpdateRequestDto.getPrioridade() != null
                ? habilidadeUpdateRequestDto.getPrioridade() : habilidade.getPrioridade());

        return toFullResponseDto(habilidadeQueryService.save(habilidade));
    }

    @Override
    public HabilidadeFullResponseDto findById(String id) {
        return toFullResponseDto(habilidadeQueryService.findByIdOrThrow(id));
    }

    @Override
    public List<HabilidadeFullResponseDto> findAll() {
        return toFullResponseDto(habilidadeQueryService.findAll());
    }

    @Override
    public void delete(String id) {
        Habilidade habilidade = habilidadeQueryService.findByIdOrThrow(id);
        List<Voluntario> voluntarios = voluntarioQueryService.findAllByHabilidadeId(habilidade.getId());
        if (voluntarios != null && !voluntarios.isEmpty()) {
            for (Voluntario voluntario : voluntarios) {
                voluntario.getHabilidadeIds().remove(habilidade.getId());
            }
            voluntarioQueryService.saveAll(voluntarios);
        }
        habilidadeQueryService.deleteById(id);
    }

    @Override
    public List<HabilidadeFullResponseDto> findByGroupId(String id) {
        return toFullResponseDto(habilidadeQueryService.findAllByGroupId(id));
    }
}
