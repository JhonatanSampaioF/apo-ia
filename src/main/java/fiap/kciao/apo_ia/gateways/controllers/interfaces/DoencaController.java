package fiap.kciao.apo_ia.gateways.controllers.interfaces;

import fiap.kciao.apo_ia.gateways.dtos.requests.domains.doencas.DoencaCreateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.requests.domains.doencas.DoencaUpdateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.responses.domains.doencas.DoencaFullResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DoencaController {
    ResponseEntity<DoencaFullResponseDto> create(DoencaCreateRequestDto doencaCreateRequestDto);
    ResponseEntity<DoencaFullResponseDto> update(String id, DoencaUpdateRequestDto doencaUpdateRequestDto);
    ResponseEntity<DoencaFullResponseDto> delete(String id);
    ResponseEntity<DoencaFullResponseDto> findById(String id);
    ResponseEntity<List<DoencaFullResponseDto>> findAll();
}
