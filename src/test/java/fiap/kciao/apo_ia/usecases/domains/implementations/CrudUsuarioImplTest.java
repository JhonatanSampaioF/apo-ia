package fiap.kciao.apo_ia.usecases.domains.implementations;

import fiap.kciao.apo_ia.domains.Usuario;
import fiap.kciao.apo_ia.gateways.dtos.requests.domains.usuarios.UsuarioCreateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.requests.domains.usuarios.UsuarioUpdateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.responses.domains.usuarios.UsuarioFullResponseDto;
import fiap.kciao.apo_ia.usecases.services.query.UsuarioQueryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CrudUsuarioImplTest {

    @Mock
    private UsuarioQueryService usuarioQueryService;

    @InjectMocks
    private CrudUsuarioImpl crudUsuario;

    @Test
    void testCreate() {
        UsuarioCreateRequestDto dto = UsuarioCreateRequestDto.builder()
                .nome("Henrique")
                .email("henrique@email.com")
                .senha("123")
                .build();

        Usuario usuario = Usuario.builder().id("u1").nome("Henrique").email("henrique@email.com").build();
        when(usuarioQueryService.save(any())).thenReturn(usuario);

        UsuarioFullResponseDto response = crudUsuario.create(dto);

        assertEquals("Henrique", response.getNome());
        assertEquals("henrique@email.com", response.getEmail());
    }

    @Test
    void testUpdate() {
        UsuarioUpdateRequestDto dto = UsuarioUpdateRequestDto.builder()
                .nome("Bruno")
                .email("bruno@email.com")
                .senha("456")
                .build();

        Usuario usuario = Usuario.builder().id("u1").nome("Henrique").build();
        when(usuarioQueryService.findByIdOrThrow("u1")).thenReturn(usuario);
        when(usuarioQueryService.save(any())).thenReturn(usuario);

        UsuarioFullResponseDto response = crudUsuario.update("u1", dto);

        assertEquals("Bruno", response.getNome());
        assertEquals("bruno@email.com", response.getEmail());
    }
}
