package fiap.kciao.apo_ia.usecases.domains.implementations;

import fiap.kciao.apo_ia.domains.Abrigado;
import fiap.kciao.apo_ia.domains.Habilidade;
import fiap.kciao.apo_ia.domains.Voluntario;
import fiap.kciao.apo_ia.gateways.dtos.requests.domains.voluntarios.VoluntarioCreateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.responses.domains.voluntarios.VoluntarioFullResponseDto;
import fiap.kciao.apo_ia.usecases.enums.ManageAction;
import fiap.kciao.apo_ia.usecases.services.query.AbrigadoQueryService;
import fiap.kciao.apo_ia.usecases.services.query.HabilidadeQueryService;
import fiap.kciao.apo_ia.usecases.services.query.VoluntarioQueryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CrudVoluntarioImplTest {

    @Mock
    private VoluntarioQueryService voluntarioQueryService;
    @Mock
    private AbrigadoQueryService abrigadoQueryService;
    @Mock
    private HabilidadeQueryService habilidadeQueryService;

    @InjectMocks
    private CrudVoluntarioImpl crudVoluntario;

    @Test
    void testCreate() {
        VoluntarioCreateRequestDto dto = VoluntarioCreateRequestDto.builder()
                .capacidade_motora("Boa")
                .abrigadoId("a1")
                .build();

        Abrigado abrigado = Abrigado.builder().id("a1").build();
        Voluntario voluntario = Voluntario.builder().id("v1").abrigadoId("a1").build();

        when(abrigadoQueryService.findByIdOrThrow("a1")).thenReturn(abrigado);
        when(voluntarioQueryService.save(any())).thenReturn(voluntario);

        VoluntarioFullResponseDto response = crudVoluntario.create(dto);

        assertEquals("a1", response.getAbrigadoId());
    }

    @Test
    void testManageHabilidadeAdd() {
        Voluntario voluntario = Voluntario.builder()
                .id("v1")
                .habilidadeIds(new ArrayList<>())
                .build();
        Habilidade habilidade = Habilidade.builder().id("h1").build();

        when(voluntarioQueryService.findByIdOrThrow("v1")).thenReturn(voluntario);
        when(habilidadeQueryService.findByIdOrThrow("h1")).thenReturn(habilidade);
        when(voluntarioQueryService.save(any())).thenReturn(voluntario);

        VoluntarioFullResponseDto response = crudVoluntario.manageHabilidade("v1", "h1", ManageAction.ADD);
        assertTrue(response.getHabilidadeIds().contains("h1"));
    }

    @Test
    void testManageAlocacaoRemove() {
        Voluntario voluntario = Voluntario.builder().id("v1").alocacao("Setor A").build();

        when(voluntarioQueryService.findByIdOrThrow("v1")).thenReturn(voluntario);
        when(voluntarioQueryService.save(any())).thenReturn(voluntario);

        VoluntarioFullResponseDto response = crudVoluntario.manageAlocacao("v1", null, ManageAction.REMOVE);
        assertNull(response.getAlocacao());
    }
}
