package fiap.kciao.apo_ia.usecases.domains.implementations;

import fiap.kciao.apo_ia.domains.GrupoHabilidade;
import fiap.kciao.apo_ia.gateways.dtos.requests.domains.gruposHabilidades.GrupoHabilidadeCreateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.requests.domains.gruposHabilidades.GrupoHabilidadeUpdateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.responses.domains.gruposHabilidades.GrupoHabilidadeFullResponseDto;
import fiap.kciao.apo_ia.usecases.domains.interfaces.CrudGrupoHabilidade;
import fiap.kciao.apo_ia.usecases.services.query.GrupoHabilidadeQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static fiap.kciao.apo_ia.gateways.mappers.domains.GrupoHabilidadeMapper.*;

@Service
@RequiredArgsConstructor
public class CrudGrupoHabilidadeImpl implements CrudGrupoHabilidade {
    private final GrupoHabilidadeQueryService grupoHabilidadeQueryService;

    @Override
    public GrupoHabilidadeFullResponseDto create(GrupoHabilidadeCreateRequestDto grupoHabilidadeCreateRequestDto) {
        return toFullResponseDto(grupoHabilidadeQueryService.save(toEntityCreate(grupoHabilidadeCreateRequestDto)));
    }

    @Override
    public GrupoHabilidadeFullResponseDto update(String id, GrupoHabilidadeUpdateRequestDto grupoHabilidadeUpdateRequestDto) {
        GrupoHabilidade grupoHabilidade = grupoHabilidadeQueryService.findByIdOrThrow(id);

        grupoHabilidade.setNome(grupoHabilidadeUpdateRequestDto.getNome());

        return toFullResponseDto(grupoHabilidadeQueryService.save(grupoHabilidade));
    }

    @Override
    public GrupoHabilidadeFullResponseDto findById(String id) {
        return toFullResponseDto(grupoHabilidadeQueryService.findByIdOrThrow(id));
    }

    @Override
    public List<GrupoHabilidadeFullResponseDto> findAll() {
        return toFullResponseDto(grupoHabilidadeQueryService.findAll());
    }

    @Override
    public void delete(String id) {
        grupoHabilidadeQueryService.deleteById(id);
    }
}
