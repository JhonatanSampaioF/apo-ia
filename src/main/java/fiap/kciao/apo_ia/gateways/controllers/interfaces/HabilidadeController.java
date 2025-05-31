package fiap.kciao.apo_ia.gateways.controllers.interfaces;

import fiap.kciao.apo_ia.gateways.dtos.requests.domains.habilidades.HabilidadeCreateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.requests.domains.habilidades.HabilidadeUpdateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.responses.domains.habilidades.HabilidadeFullResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface HabilidadeController {
    ResponseEntity<HabilidadeFullResponseDto> create(HabilidadeCreateRequestDto habilidadeCreateRequestDto);
    ResponseEntity<HabilidadeFullResponseDto> update(String id, HabilidadeUpdateRequestDto habilidadeUpdateRequestDto);
    ResponseEntity<Void> delete(String id);
    ResponseEntity<HabilidadeFullResponseDto> findById(String id);
    ResponseEntity<List<HabilidadeFullResponseDto>> findAll();
}
