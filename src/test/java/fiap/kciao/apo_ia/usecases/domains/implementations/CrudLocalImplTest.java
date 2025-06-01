package fiap.kciao.apo_ia.usecases.domains.implementations;

import fiap.kciao.apo_ia.domains.Local;
import fiap.kciao.apo_ia.gateways.dtos.requests.domains.locais.LocalCreateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.requests.domains.locais.LocalUpdateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.responses.domains.locais.LocalFullResponseDto;
import fiap.kciao.apo_ia.usecases.services.query.LocalQueryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CrudLocalImplTest {

    @Mock
    private LocalQueryService localQueryService;

    @InjectMocks
    private CrudLocalImpl crudLocal;

    @Test
    void testCreate() {
        LocalCreateRequestDto dto = LocalCreateRequestDto.builder()
                .nome("Centro A")
                .endereco("Rua A, 123")
                .capacidade(50)
                .qtd_abrigados(10)
                .build();

        Local local = Local.builder().id("l1").nome("Centro A").build();
        when(localQueryService.save(any())).thenReturn(local);

        LocalFullResponseDto response = crudLocal.create(dto);

        assertNotNull(response);
        assertEquals("Centro A", response.getNome());
    }

    @Test
    void testUpdate() {
        LocalUpdateRequestDto dto = LocalUpdateRequestDto.builder()
                .nome("Centro B")
                .endereco("Rua B, 456")
                .capacidade(100)
                .qtd_abrigados(20)
                .build();

        Local local = Local.builder().id("l1").nome("Centro A").build();
        when(localQueryService.findByIdOrThrow("l1")).thenReturn(local);
        when(localQueryService.save(any())).thenReturn(local);

        LocalFullResponseDto response = crudLocal.update("l1", dto);

        assertEquals("Centro B", response.getNome());
        assertEquals(100, response.getCapacidade());
    }
}
