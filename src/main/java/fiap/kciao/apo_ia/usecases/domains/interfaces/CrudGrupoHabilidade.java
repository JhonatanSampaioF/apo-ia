package fiap.kciao.apo_ia.usecases.domains.interfaces;

import fiap.kciao.apo_ia.gateways.dtos.requests.domains.gruposHabilidades.GrupoHabilidadeCreateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.requests.domains.gruposHabilidades.GrupoHabilidadeUpdateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.responses.domains.gruposHabilidades.GrupoHabilidadeFullResponseDto;

import java.util.List;

public interface CrudGrupoHabilidade {
    public GrupoHabilidadeFullResponseDto create(GrupoHabilidadeCreateRequestDto grupoHabilidadeCreateRequestDto);
    public GrupoHabilidadeFullResponseDto update(String id, GrupoHabilidadeUpdateRequestDto grupoHabilidadeUpdateRequestDto);
    public GrupoHabilidadeFullResponseDto findById(String id);
    public List<GrupoHabilidadeFullResponseDto> findAll();
    public void delete(String id);
}
