package fiap.kciao.apo_ia.usecases.domains.implementations;

import fiap.kciao.apo_ia.domains.Usuario;
import fiap.kciao.apo_ia.gateways.dtos.requests.domains.usuarios.UsuarioCreateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.requests.domains.usuarios.UsuarioUpdateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.responses.domains.usuarios.UsuarioFullResponseDto;
import fiap.kciao.apo_ia.usecases.domains.interfaces.CrudUsuario;
import fiap.kciao.apo_ia.usecases.services.query.UsuarioQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static fiap.kciao.apo_ia.gateways.mappers.domains.UsuarioMapper.*;

@Service
@RequiredArgsConstructor
public class CrudUsuarioImpl implements CrudUsuario {
    private final UsuarioQueryService usuarioQueryService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UsuarioFullResponseDto create(UsuarioCreateRequestDto usuarioCreateRequestDto) {
        Usuario usuario = usuarioQueryService.findByEmailOrNull(usuarioCreateRequestDto.getEmail());
        if (usuario != null) {
            throw new IllegalArgumentException("Email já utilizado");
        }
        usuario = toEntityCreate(usuarioCreateRequestDto);
        usuario.setSenha(passwordEncoder.encode(usuarioCreateRequestDto.getSenha()));

        return toFullResponseDto(usuarioQueryService.save(usuario));
    }

    @Override
    public UsuarioFullResponseDto update(String id, UsuarioUpdateRequestDto usuarioUpdateRequestDto) {
        Usuario usuario = usuarioQueryService.findByIdOrThrow(id);

        usuario.setNome(usuarioUpdateRequestDto.getNome() != null
                ? usuarioUpdateRequestDto.getNome() : usuario.getNome());
        usuario.setEmail(usuarioUpdateRequestDto.getEmail() != null
                ? usuarioUpdateRequestDto.getEmail() : usuario.getEmail());
        usuario.setSenha(usuarioUpdateRequestDto.getSenha() != null
                ? passwordEncoder.encode(usuarioUpdateRequestDto.getSenha()) : usuario.getSenha());

        return toFullResponseDto(usuarioQueryService.save(usuario));
    }

    @Override
    public UsuarioFullResponseDto findById(String id) {
        return toFullResponseDto(usuarioQueryService.findByIdOrThrow(id));
    }

    @Override
    public List<UsuarioFullResponseDto> findAll() {
        return toFullResponseDto(usuarioQueryService.findAll());
    }

    @Override
    public void delete(String id) {
        usuarioQueryService.deleteById(id);
    }
}
