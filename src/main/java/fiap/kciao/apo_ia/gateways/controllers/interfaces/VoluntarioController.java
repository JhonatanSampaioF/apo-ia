package fiap.kciao.apo_ia.gateways.controllers.interfaces;

import fiap.kciao.apo_ia.gateways.dtos.requests.domains.voluntarios.VoluntarioCreateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.requests.domains.voluntarios.VoluntarioUpdateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.responses.domains.voluntarios.VoluntarioFullResponseDto;
import fiap.kciao.apo_ia.usecases.enums.ManageAction;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface VoluntarioController {
    ResponseEntity<VoluntarioFullResponseDto> create(VoluntarioCreateRequestDto voluntarioCreateRequestDto);
    ResponseEntity<VoluntarioFullResponseDto> update(String id, VoluntarioUpdateRequestDto voluntarioUpdateRequestDto);
    ResponseEntity<VoluntarioFullResponseDto> delete(String id);
    ResponseEntity<VoluntarioFullResponseDto> findById(String id);
    ResponseEntity<List<VoluntarioFullResponseDto>> findAll();
    ResponseEntity<VoluntarioFullResponseDto> manageHabilidade(String voluntarioId, String habilidadeId, ManageAction action);
    ResponseEntity<VoluntarioFullResponseDto> manageAlocacao(String voluntarioId, String alocacaoId, ManageAction action);
}
