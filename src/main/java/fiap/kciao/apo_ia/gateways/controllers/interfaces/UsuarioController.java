package fiap.kciao.apo_ia.gateways.controllers.interfaces;

import fiap.kciao.apo_ia.gateways.dtos.requests.domains.usuarios.UsuarioCreateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.requests.domains.usuarios.UsuarioUpdateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.responses.domains.usuarios.UsuarioFullResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UsuarioController {
    ResponseEntity<UsuarioFullResponseDto> create(UsuarioCreateRequestDto usuarioCreateRequestDto);
    ResponseEntity<UsuarioFullResponseDto> update(String id, UsuarioUpdateRequestDto usuarioUpdateRequestDto);
    ResponseEntity<UsuarioFullResponseDto> delete(String id);
    ResponseEntity<UsuarioFullResponseDto> findById(String id);
    ResponseEntity<List<UsuarioFullResponseDto>> findAll();
}
