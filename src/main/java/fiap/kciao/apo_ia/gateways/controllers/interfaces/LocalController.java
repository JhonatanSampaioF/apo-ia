package fiap.kciao.apo_ia.gateways.controllers.interfaces;

import fiap.kciao.apo_ia.gateways.dtos.requests.domains.locais.LocalCreateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.requests.domains.locais.LocalUpdateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.responses.domains.locais.LocalFullResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface LocalController {
    ResponseEntity<LocalFullResponseDto> create(LocalCreateRequestDto localCreateRequestDto);
    ResponseEntity<LocalFullResponseDto> update(String id, LocalUpdateRequestDto localUpdateRequestDto);
    ResponseEntity<LocalFullResponseDto> delete(String id);
    ResponseEntity<LocalFullResponseDto> findById(String id);
    ResponseEntity<List<LocalFullResponseDto>> findAll();
}
