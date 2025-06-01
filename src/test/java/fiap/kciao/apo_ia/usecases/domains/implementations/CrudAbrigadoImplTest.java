package fiap.kciao.apo_ia.usecases.domains.implementations;

import fiap.kciao.apo_ia.domains.Abrigado;
import fiap.kciao.apo_ia.domains.Doenca;
import fiap.kciao.apo_ia.domains.Local;
import fiap.kciao.apo_ia.gateways.dtos.requests.domains.abrigados.AbrigadoCreateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.requests.domains.abrigados.AbrigadoUpdateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.responses.domains.abrigados.AbrigadoFullResponseDto;
import fiap.kciao.apo_ia.usecases.enums.ManageAction;
import fiap.kciao.apo_ia.usecases.services.query.AbrigadoQueryService;
import fiap.kciao.apo_ia.usecases.services.query.DoencaQueryService;
import fiap.kciao.apo_ia.usecases.services.query.LocalQueryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CrudAbrigadoImplTest {

    @Mock
    private AbrigadoQueryService abrigadoQueryService;
    @Mock
    private LocalQueryService localQueryService;
    @Mock
    private DoencaQueryService doencaQueryService;
    @InjectMocks
    private CrudAbrigadoImpl crudAbrigado;

    @Test
    void testCreate() {
        AbrigadoCreateRequestDto dto = AbrigadoCreateRequestDto.builder()
                .nome("João")
                .idade(30)
                .altura(1.75)
                .peso(70.0)
                .cpf("123.456.789-00")
                .voluntario(false)
                .ferimento("nenhum")
                .localId("local123")
                .build();

        Local local = Local.builder().id("local123").build();
        Abrigado abrigado = Abrigado.builder().id("id1").localId("local123").build();

        when(localQueryService.findByIdOrThrow("local123")).thenReturn(local);
        when(abrigadoQueryService.save(any())).thenReturn(abrigado);

        AbrigadoFullResponseDto response = crudAbrigado.create(dto);

        assertNotNull(response);
        verify(localQueryService).findByIdOrThrow("local123");
        verify(abrigadoQueryService, times(2)).save(any());
    }

    @Test
    void testUpdate() {
        AbrigadoUpdateRequestDto dto = AbrigadoUpdateRequestDto.builder()
                .nome("Maria")
                .idade(25)
                .altura(1.65)
                .peso(60.0)
                .cpf("987.654.321-00")
                .voluntario(true)
                .ferimento("leve")
                .build();

        Abrigado abrigado = Abrigado.builder().id("id1").build();

        when(abrigadoQueryService.findByIdOrThrow("id1")).thenReturn(abrigado);
        when(abrigadoQueryService.save(any())).thenReturn(abrigado);

        AbrigadoFullResponseDto response = crudAbrigado.update("id1", dto);

        assertNotNull(response);
        verify(abrigadoQueryService).save(abrigado);
    }

    @Test
    void testManageDoencaAdd() {
        Abrigado abrigado = Abrigado.builder().id("a1").doencaIds(new ArrayList<>()).build();
        Doenca doenca = Doenca.builder().id("d1").build();

        when(abrigadoQueryService.findByIdOrThrow("a1")).thenReturn(abrigado);
        when(doencaQueryService.findByIdOrThrow("d1")).thenReturn(doenca);
        when(abrigadoQueryService.save(any())).thenReturn(abrigado);

        AbrigadoFullResponseDto response = crudAbrigado.manageDoenca("d1", "a1", ManageAction.ADD);
        assertTrue(response.getDoencaIds().contains("d1"));
    }

    @Test
    void testManageDoencaRemove() {
        Abrigado abrigado = Abrigado.builder().id("a1").doencaIds(new ArrayList<>(List.of("d1"))).build();
        Doenca doenca = Doenca.builder().id("d1").build();

        when(abrigadoQueryService.findByIdOrThrow("a1")).thenReturn(abrigado);
        when(doencaQueryService.findByIdOrThrow("d1")).thenReturn(doenca);
        when(abrigadoQueryService.save(any())).thenReturn(abrigado);

        AbrigadoFullResponseDto response = crudAbrigado.manageDoenca("d1", "a1", ManageAction.REMOVE);
        assertFalse(response.getDoencaIds().contains("d1"));
    }
}
