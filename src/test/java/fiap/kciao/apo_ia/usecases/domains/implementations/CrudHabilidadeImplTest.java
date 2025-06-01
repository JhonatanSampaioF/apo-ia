package fiap.kciao.apo_ia.usecases.domains.implementations;

import fiap.kciao.apo_ia.domains.GrupoHabilidade;
import fiap.kciao.apo_ia.domains.Habilidade;
import fiap.kciao.apo_ia.gateways.dtos.requests.domains.habilidades.HabilidadeCreateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.requests.domains.habilidades.HabilidadeUpdateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.responses.domains.habilidades.HabilidadeFullResponseDto;
import fiap.kciao.apo_ia.usecases.services.query.GrupoHabilidadeQueryService;
import fiap.kciao.apo_ia.usecases.services.query.HabilidadeQueryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CrudHabilidadeImplTest {

    @Mock
    private GrupoHabilidadeQueryService grupoHabilidadeQueryService;
    @Mock
    private HabilidadeQueryService habilidadeQueryService;

    @InjectMocks
    private CrudHabilidadeImpl crudHabilidade;

    @Test
    void testCreate() {
        HabilidadeCreateRequestDto dto = HabilidadeCreateRequestDto.builder()
                .nome("Organizar estoques")
                .prioridade(1)
                .grupoHabilidadeId("g1")
                .build();

        GrupoHabilidade grupo = GrupoHabilidade.builder().id("g1").nome("Logística").build();
        Habilidade habilidade = Habilidade.builder().id("h1").nome("Organizar estoques").grupoHabilidadeId("g1").build();

        when(grupoHabilidadeQueryService.findByIdOrThrow("g1")).thenReturn(grupo);
        when(habilidadeQueryService.save(any())).thenReturn(habilidade);

        HabilidadeFullResponseDto response = crudHabilidade.create(dto);

        assertNotNull(response);
        assertEquals("g1", response.getGrupoHabilidadeId());
        assertEquals("Organizar estoques", response.getNome());
    }

    @Test
    void testUpdate() {
        HabilidadeUpdateRequestDto dto = HabilidadeUpdateRequestDto.builder()
                .nome("Apoio emocional")
                .prioridade(2)
                .build();

        Habilidade original = Habilidade.builder().id("h1").nome("Psicologia").prioridade(1).build();

        when(habilidadeQueryService.findByIdOrThrow("h1")).thenReturn(original);
        when(habilidadeQueryService.save(any())).thenReturn(original);

        HabilidadeFullResponseDto response = crudHabilidade.update("h1", dto);

        assertEquals("Apoio emocional", response.getNome());
        assertEquals(2, response.getPrioridade());
    }
}
