package fiap.kciao.apo_ia.usecases.domains.implementations;

import fiap.kciao.apo_ia.domains.GrupoHabilidade;
import fiap.kciao.apo_ia.gateways.dtos.requests.domains.gruposHabilidades.GrupoHabilidadeCreateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.requests.domains.gruposHabilidades.GrupoHabilidadeUpdateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.responses.domains.gruposHabilidades.GrupoHabilidadeFullResponseDto;
import fiap.kciao.apo_ia.usecases.services.query.GrupoHabilidadeQueryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CrudGrupoHabilidadeImplTest {

    @Mock
    private GrupoHabilidadeQueryService grupoHabilidadeQueryService;

    @InjectMocks
    private CrudGrupoHabilidadeImpl crudGrupoHabilidade;

    @Test
    void testCreate() {
        GrupoHabilidadeCreateRequestDto dto = GrupoHabilidadeCreateRequestDto.builder()
                .nome("Saúde")
                .grupoHabilidadeId("dummy") // campo não usado diretamente
                .build();

        GrupoHabilidade saved = GrupoHabilidade.builder().id("id1").nome("Saúde").build();
        when(grupoHabilidadeQueryService.save(any())).thenReturn(saved);

        GrupoHabilidadeFullResponseDto response = crudGrupoHabilidade.create(dto);

        assertNotNull(response);
        assertEquals("Saúde", response.getNome());
    }

    @Test
    void testUpdate() {
        GrupoHabilidadeUpdateRequestDto dto = GrupoHabilidadeUpdateRequestDto.builder()
                .nome("Logística")
                .build();

        GrupoHabilidade original = GrupoHabilidade.builder().id("g1").nome("Saúde").build();
        when(grupoHabilidadeQueryService.findByIdOrThrow("g1")).thenReturn(original);
        when(grupoHabilidadeQueryService.save(any())).thenReturn(original);

        GrupoHabilidadeFullResponseDto response = crudGrupoHabilidade.update("g1", dto);

        assertEquals("Logística", response.getNome());
    }
}
