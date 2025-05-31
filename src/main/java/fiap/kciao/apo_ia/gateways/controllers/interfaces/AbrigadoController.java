package fiap.kciao.apo_ia.gateways.controllers.interfaces;

import fiap.kciao.apo_ia.gateways.dtos.requests.domains.abrigados.AbrigadoCreateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.requests.domains.abrigados.AbrigadoUpdateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.responses.domains.abrigados.AbrigadoFullResponseDto;
import fiap.kciao.apo_ia.usecases.enums.ManageAction;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AbrigadoController {
    ResponseEntity<AbrigadoFullResponseDto> save(AbrigadoCreateRequestDto abrigadoCreateRequestDto);
    ResponseEntity<AbrigadoFullResponseDto> update(String id, AbrigadoUpdateRequestDto abrigadoUpdateRequestDto);
    ResponseEntity<AbrigadoFullResponseDto> delete(String id);
    ResponseEntity<AbrigadoFullResponseDto> findById(String id);
    ResponseEntity<List<AbrigadoFullResponseDto>> findAll();
    ResponseEntity<AbrigadoFullResponseDto> manageDoenca(String doencaId, String abrigadoId, ManageAction action);
}
