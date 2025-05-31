package fiap.kciao.apo_ia.usecases.domains.interfaces;

import fiap.kciao.apo_ia.gateways.dtos.requests.domains.usuarios.UsuarioCreateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.requests.domains.usuarios.UsuarioUpdateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.responses.domains.usuarios.UsuarioFullResponseDto;

import java.util.List;

public interface CrudUsuario {
    public UsuarioFullResponseDto create(UsuarioCreateRequestDto usuarioCreateRequestDto);
    public UsuarioFullResponseDto update(String id, UsuarioUpdateRequestDto usuarioUpdateRequestDto);
    public UsuarioFullResponseDto findById(String id);
    public List<UsuarioFullResponseDto> findAll();
    public void delete(String id);
}
