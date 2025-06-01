package fiap.kciao.apo_ia.usecases.domains.implementations;

import fiap.kciao.apo_ia.domains.Doenca;
import fiap.kciao.apo_ia.gateways.dtos.requests.domains.doencas.DoencaCreateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.requests.domains.doencas.DoencaUpdateRequestDto;
import fiap.kciao.apo_ia.gateways.dtos.responses.domains.doencas.DoencaFullResponseDto;
import fiap.kciao.apo_ia.usecases.services.query.DoencaQueryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CrudDoencaImplTest {

    @Mock
    private DoencaQueryService doencaQueryService;
    @InjectMocks
    private CrudDoencaImpl crudDoenca;

    @Test
    void testCreate() {
        DoencaCreateRequestDto dto = DoencaCreateRequestDto.builder()
                .nome("Gripe")
                .gravidade("leve")
                .build();

        Doenca saved = Doenca.builder().id("id1").nome("Gripe").gravidade("leve").build();
        when(doencaQueryService.save(any())).thenReturn(saved);

        DoencaFullResponseDto response = crudDoenca.create(dto);
        assertEquals("Gripe", response.getNome());
    }

    @Test
    void testUpdate() {
        Doenca existing = Doenca.builder().id("id1").nome("Febre").gravidade("leve").build();
        DoencaUpdateRequestDto dto = DoencaUpdateRequestDto.builder().nome("Dengue").gravidade("alta").build();

        when(doencaQueryService.findByIdOrThrow("id1")).thenReturn(existing);
        when(doencaQueryService.save(any())).thenReturn(existing);

        DoencaFullResponseDto response = crudDoenca.update("id1", dto);
        assertEquals("Dengue", response.getNome());
    }
}
