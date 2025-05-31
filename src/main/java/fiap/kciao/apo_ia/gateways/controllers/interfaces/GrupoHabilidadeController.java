package fiap.kciao.apo_ia.gateways.controllers.interfaces;

import fiap.kciao.apo_ia.gateways.dtos.requests.domains.gruposHabilidades.GrupoHabilidadeCreateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.requests.domains.gruposHabilidades.GrupoHabilidadeUpdateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.responses.domains.gruposHabilidades.GrupoHabilidadeFullResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface GrupoHabilidadeController {
    ResponseEntity<GrupoHabilidadeFullResponseDto> create(GrupoHabilidadeCreateRequestDto grupoHabilidadeCreateRequestDto);
    ResponseEntity<GrupoHabilidadeFullResponseDto> update(String id, GrupoHabilidadeUpdateRequestDto grupoHabilidadeUpdateRequestDto);
    ResponseEntity<Void> delete(String id);
    ResponseEntity<GrupoHabilidadeFullResponseDto> findById(String id);
    ResponseEntity<List<GrupoHabilidadeFullResponseDto>> findAll();
}
